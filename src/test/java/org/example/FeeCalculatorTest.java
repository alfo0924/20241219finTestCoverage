package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FeeCalculatorTest {
    @Mock
    private GitHubService gitHubService;

    private FeeCalculator calculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculator = new FeeCalculator(gitHubService);
    }

    @Test
    void testGradeTooLow() {
        assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateFee(1, 0, 0, 0, ""));
    }

    @Test
    void testNoDiscount() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(500, calculator.calculateFee(2, 5, 0, 0, ""));
    }

    @Test
    void testAbsenceDiscount() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(450, calculator.calculateFee(2, 4, 0, 0, ""));
    }

    @Test
    void testWPMDiscountOver60() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(450, calculator.calculateFee(2, 10, 0, 61, ""));
    }

    @Test
    void testWPMDiscountOver80() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(400, calculator.calculateFee(2, 10, 0, 81, ""));
    }

    @Test
    void testWPMDiscountOver100() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(350, calculator.calculateFee(2, 10, 0, 101, ""));
    }

    @Test
    void testTypingMinutesDiscountGrade2Low() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(400, calculator.calculateFee(2, 10, 5, 0, ""));
    }

    @Test
    void testTypingMinutesDiscountGrade2High() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(300, calculator.calculateFee(2, 10, 10, 0, ""));
    }

    @Test
    void testTypingMinutesDiscountGrade3Low() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(400, calculator.calculateFee(3, 10, 10, 0, ""));
    }

    @Test
    void testTypingMinutesDiscountGrade3High() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(300, calculator.calculateFee(3, 10, 15, 0, ""));
    }

    @Test
    void testTypingMinutesDiscountGrade4Low() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(400, calculator.calculateFee(4, 10, 15, 0, ""));
    }

    @Test
    void testTypingMinutesDiscountGrade4High() {
        when(gitHubService.getLines(anyString())).thenReturn(0);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(300, calculator.calculateFee(4, 10, 20, 0, ""));
    }

    @Test
    void testProgrammingDiscountGrade2Small() {
        when(gitHubService.getLines(anyString())).thenReturn(500);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(500, calculator.calculateFee(2, 10, 0, 0, "repo"));
    } //475

    @Test
    void testProgrammingDiscountGrade2Max() {
        when(gitHubService.getLines(anyString())).thenReturn(5000);
        when(gitHubService.getWMC(anyString())).thenReturn(0);

        assertEquals(300, calculator.calculateFee(2, 10, 0, 0, "repo"));
    }

    @Test
    void testProgrammingDiscountHigherGradeWithLowWMC() {
        when(gitHubService.getLines(anyString())).thenReturn(5000);
        when(gitHubService.getWMC(anyString())).thenReturn(50);

        assertEquals(500, calculator.calculateFee(3, 10, 0, 0, "repo"));
    }

    @Test
    void testProgrammingDiscountHigherGradeWithHighWMC() {
        when(gitHubService.getLines(anyString())).thenReturn(5000);
        when(gitHubService.getWMC(anyString())).thenReturn(51);

        assertEquals(300, calculator.calculateFee(3, 10, 0, 0, "repo"));
    }

    @Test
    void testMultipleDiscountsHighestWins() {
        when(gitHubService.getLines(anyString())).thenReturn(5000);
        when(gitHubService.getWMC(anyString())).thenReturn(51);

        // 多重折扣：曠課(50)、WPM(150)、深蹲打字(200)、程式成癮(200)
        // 應該選擇最高的折扣200
        assertEquals(300, calculator.calculateFee(3, 4, 15, 101, "repo"));
    }
}
