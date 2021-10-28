package com.application.controller;

import com.application.dto.ReportDTO;
import com.application.dto.ShoppingDTO;
import com.application.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @GetMapping("/shopping")
    public List<ShoppingDTO> getShops() {
        return shoppingService.getAll();
    }

    @GetMapping("/shopping/shopuser/{useridentifier}")
    public List<ShoppingDTO> getByUser(@PathVariable String useridentifier) {
        return shoppingService.getByUser(useridentifier);
    }

    @GetMapping("shopping/shopbydate")
    public List<ShoppingDTO> getByDate(@RequestBody ShoppingDTO shoppingDTO) {
        return shoppingService.getByDate(shoppingDTO);
    }

    @GetMapping("shopping/{id}")
    public ShoppingDTO findById(@PathVariable Long id) {
        return shoppingService.findById(id);
    }

    @PostMapping("/shopping")
    public ShoppingDTO newShop(@RequestHeader(name = "key") String key,
                               @RequestBody ShoppingDTO shoppingDTO) {
        return shoppingService.save(shoppingDTO, key);
    }

    @GetMapping("/shopping/search")
    public List<ShoppingDTO> getShopsByFilter(@RequestParam(name = "dataInicio", required = true)
                                              @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                              @RequestParam(name = "dataFim", required = false)
                                              @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
                                              @RequestParam(name = "valorMinimo") Float valorMinimo) {
        return shoppingService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ReportDTO getReportByDate(@RequestParam(name = "dataInicio", required = true)
                                     @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                     @RequestParam(name = "dataFim", required = true)
                                     @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
        return shoppingService.getReportByDate(dataInicio, dataFim);
    }
}
