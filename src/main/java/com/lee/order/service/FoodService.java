package com.lee.order.service;

import com.lee.order.dto.FoodDto;
import com.lee.order.exceptions.ExceptionMsg;
import com.lee.order.model.Food;
import com.lee.order.model.Restaurant;
import com.lee.order.repository.FoodRepository;
import com.lee.order.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    //메뉴 전체 조회 
    public List<FoodDto> menu(Long restaurantId) {
        List<Food> menu = restaurantRepository.findMenus(restaurantId);
        List<FoodDto> response = new LinkedList<>();
        for (Food food : menu) {
            response.add(new FoodDto(food));
        }
        return response;
    }

    // 음식 등록
    @Transactional
    public void register(Long restaurantId, List<FoodDto> newMenu) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("데이터가 없습니다")
        );

        //Food FoodList -> String 음식 이름 리스트
        List<String> oriMenu = new LinkedList<String>();
        for (Food menu : restaurant.getMenu()) {
            oriMenu.add(menu.getName());
        }

        // 신 메뉴가 타당한지 확인 후 모두 DB 저장
        List<Food> regiNewMenu = foodRepository.saveAll(validateNewFoods(oriMenu, newMenu));
        // 레스토랑 메뉴에 추가
        restaurant.getMenu().addAll(regiNewMenu);

        // 레스토랑 DB 반영
        restaurantRepository.save(restaurant);
    }

    // 추가 음식 타당성 검사
    public List<Food> validateNewFoods(List<String> oriMenu, List<FoodDto> newMenu) {

        List<Food> allowAddMenu = new LinkedList<>();
        List<String> menuNames = new LinkedList<>();
        if (newMenu.size() == 0) throw new IllegalArgumentException();

        for (FoodDto food : newMenu) {

            if (oriMenu.size()!=0 && oriMenu.contains(food.getName())) {
                throw new IllegalArgumentException(ExceptionMsg.RESTAURANT_FOOD_DUPLICATE.getMsg());
            } else if (food.getPrice() < 100 || food.getPrice() > 1000000) {
                throw new IllegalArgumentException(ExceptionMsg.FOOD_PRICES_PRICE_RANGE.getMsg());
            } else if (food.getPrice() % 100 != 0) {
                throw new IllegalArgumentException(ExceptionMsg.ILLEGAL_FOOD_PRICES_UNIT.getMsg());
            }else if(menuNames.contains(food.getName())){
                throw new IllegalArgumentException(ExceptionMsg.RESTAURANT_FOOD_DUPLICATE.getMsg());
            } else {
                menuNames.add(food.getName());
                allowAddMenu.add(new Food(food));
            }
        }
        return allowAddMenu;
    }
}
