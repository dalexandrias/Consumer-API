package com.application.service;

import com.application.dto.ItemDTO;
import com.application.dto.ProductDTO;
import com.application.dto.ReportDTO;
import com.application.dto.ShoppingDTO;
import com.application.repository.ShoppingRepository;
import com.application.dto.DTOConverter;
import com.application.model.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public List<ShoppingDTO> getAll() {
        List<Shopping> Shopping = shoppingRepository.findAll();
        return Shopping.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ShoppingDTO> getByUser(String userIdentifier) {
        List<Shopping> Shopping = shoppingRepository.findAllByUserIdentifier(userIdentifier);
        return Shopping.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShoppingDTO findById(Long id) {
        Optional<Shopping> shopping = shoppingRepository.findById(id);

        if (shopping.isPresent()) {
            return DTOConverter.convert(shopping.get());
        }
        return null;
    }

    public List<ShoppingDTO> getByDate(ShoppingDTO shoppingDtoDate) {
        List<Shopping> shopping = shoppingRepository.findAllByDateGreaterThanEqual(shoppingDtoDate.getDate());
        return shopping.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ShoppingDTO save(ShoppingDTO shoppingDtoSave, String key) {
        //Valida se o usuário existe na user api
        if (null == userService.getUserByCpf(shoppingDtoSave.getUserIdentifier(), key)) {
            return null;
        }

        if (!validateProducts(shoppingDtoSave.getItems())) {
            return null;
        }
        shoppingDtoSave.setTotal(shoppingDtoSave.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce((float) 0, Float::sum));

        shoppingDtoSave.setDate(new Date());

        Shopping shopping = Shopping.convert(shoppingDtoSave);
        shopping = shoppingRepository.save(shopping);
        return DTOConverter.convert(shopping);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO itemDTO : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(itemDTO.getProductIdentifier());
            if (null == productDTO) {
                return false;
            }
            itemDTO.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public List<ShoppingDTO> getShopsByFilter(Date dataInicio, Date dataFim, Float valorMinimo) {
        List<Shopping> shoppings = shoppingRepository.getReportByFilters(dataInicio, dataFim, valorMinimo);
        return shoppings.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return shoppingRepository.getReportByDate(dataInicio, dataFim);
    }
}
