package com.one234gift.orderservice.command.presentation;

import com.one234gift.orderservice.command.application.ChangeOrderService;
import com.one234gift.orderservice.common.APIResponse;
import com.one234gift.orderservice.common.CommandException;
import com.one234gift.orderservice.domain.model.*;
import com.one234gift.orderservice.domain.read.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/order/{orderId}")
public class ChangeOrderAPI {
    @Autowired private ChangeOrderService changeOrderService;

    @PutMapping("delivery")
    public APIResponse changeDelivery(@PathVariable long orderId, @Valid @RequestBody ChangeDelivery delivery, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = changeOrderService.changeDelivery(orderId, delivery, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }

    @PutMapping("quantity")
    public APIResponse changeQuantity(@PathVariable long orderId, @Valid @RequestBody ChangeQuantity quantity, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = changeOrderService.changeQuantity(orderId, quantity, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }

    @PutMapping("purchase-price")
    public APIResponse changePurchasePrice(@PathVariable long orderId, @Valid @RequestBody ChangePurchasePrice purchasePrice, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = changeOrderService.changePurchasePrice(orderId, purchasePrice, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }

    @PutMapping("sale-price")
    public APIResponse changeSalePrice(@PathVariable long orderId, @Valid @RequestBody ChangeSalePrice salePrice, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = changeOrderService.changeSalePrice(orderId, salePrice, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }

    @PutMapping("content")
    public APIResponse changeContent(@PathVariable long orderId, @Valid @RequestBody ChangeContent content, Errors errors, Principal principal){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        OrderModel orderModel = changeOrderService.changeContent(orderId, content, principal.getName());
        return new APIResponse(orderModel, HttpStatus.OK);
    }


}
