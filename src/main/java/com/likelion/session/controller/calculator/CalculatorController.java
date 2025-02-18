package com.likelion.session.controller.calculator;

import com.likelion.session.dto.calculator.request.CalculatorAddRequest;
import com.likelion.session.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 💡 CalculatorController
 * - 숫자를 더하거나 곱하는 기능을 제공하는 **REST API 컨트롤러**
 * - `@RestController`를 사용하여 API 응답을 JSON 형식으로 반환
 */
@RestController
public class CalculatorController {
    /**
     * ✅ GET 방식으로 두 숫자의 합을 구하는 API
     * - 요청: `/add?number1=5&number2=10`
     * - 응답: 15
     *
     * 📌 @RequestParam을 사용하지 않고 DTO로 받음 (객체 바인딩)
     * - `CalculatorAddRequest`라는 DTO 객체를 사용해 `number1`과 `number2` 값을 받음
     * - `CalculatorAddRequest`는 GET 요청의 쿼리 파라미터를 자동으로 매핑함
     */
    /*@GetMapping("/add")
    public int addTwoNumbers(
            @RequestParam int number1,
            @RequestParam int number2
    ) {
        return number1 + number2;
    }*/

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    /**
     * ✅ POST 방식으로 두 숫자의 곱을 구하는 API
     * - 요청: `POST /multiply` (JSON 요청 바디 필요)
     * - 요청 바디 예제:
     *   ```json
     *   {
     *       "number1": 3,
     *       "number2": 4
     *   }
     *   ```
     * - 응답: 12
     *
     * 📌 @RequestBody를 사용하여 JSON 데이터를 `CalculatorMultiplyRequest` 객체로 변환
     */
    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}