package com.likelion.session.dto.calculator.request;

/**
 * 💡 CalculatorMultiplyRequest (DTO)
 * - POST 요청에서 두 개의 숫자를 JSON으로 받아오기 위한 DTO
 */
public class CalculatorMultiplyRequest {
    private final int number1; // 첫 번째 숫자
    private final int number2; // 두 번째 숫자

    /**
     * ✅ 생성자 (생성자를 통해 값이 전달됨)
     */
    public CalculatorMultiplyRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    /**
     * ✅ Getter 메서드 (각 필드의 값을 가져오기 위해 사용)
     */
    public int getNumber1() {
        return number1;
    }
    public int getNumber2() {
        return number2;
    }
}