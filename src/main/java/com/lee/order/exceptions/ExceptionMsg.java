package com.lee.order.exceptions;

public enum ExceptionMsg {
    // Common
    RESTAURANT_IS_NULL("레스토랑이 없습니다."),
    COMMON_ERROR("관리자 문의"),
    // FOOD
    RESTAURANT_FOOD_DUPLICATE("중복된 이름의 음식이 존재합니다."),
    FOOD_PRICES_PRICE_RANGE("음식의 허용가격은 100원 ~ 1,000,000원 이내 입니다."),
    ILLEGAL_FOOD_PRICES_UNIT("음식 가격이 100원 단위로 입력되어야 합니다."),

    // Order
    ILLEGAL_FOOD_ORDER_QUANTITY("음식 주문 수량은 1 ~ 100이하 입니다."),
    CANT_FIND_FOOD("해당 음식이 없습니다."),
    ILLEGAL_TOTAL_PRICE("음식점의 최소 주문 가격을 넘지 않았습니다."),

    // Restaurant
    ILLEGAL_MIN_ORDER_PRICE_RANGE("최소주문 가격 허용값은  1,000원 ~ 100,000원입니다."),
    ILLEGAL_MIN_ORDER_PRICE_UNIT("100원 단위로 입력하지 않았습니다."),

    ILLEGAL_DELIVERY_FEE_RANGE("기본 배달비 허용값을 벗어났습니다."),
    ILLEGAL_DELIVERY_FEE_UNIT("기본 배달비 500원 단위로 입력하지 않았습니다.");

    private final String msg;
    ExceptionMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }

}
