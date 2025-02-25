package com.likelion.session.controller.calculator;

import com.likelion.session.dto.calculator.request.CalculatorAddRequest;
import com.likelion.session.dto.calculator.request.CalculatorMultiplyRequest;
import com.likelion.session.service.calculator.CalculatorService;
import org.springframework.web.bind.annotation.*;

/**
 * 💡 CalculatorController
 * - 숫자를 더하거나 곱하는 기능을 제공하는 REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    // ✅ 생성자 주입 (Constructor Injection)
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * ✅ GET 방식으로 두 숫자의 합을 구하는 API
     * 요청 예시: `/api/calculator/add?number1=5&number2=10`
     */
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return calculatorService.add(request.getNumber1(), request.getNumber2());
    }

    /**
     * ✅ POST 방식으로 두 숫자의 곱을 구하는 API
     * 요청 예시: `POST /api/calculator/multiply` (JSON 요청 바디)
     */
    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return calculatorService.multiply(request.getNumber1(), request.getNumber2());
    }
}