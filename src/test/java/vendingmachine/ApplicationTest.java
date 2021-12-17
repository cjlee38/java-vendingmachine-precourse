package vendingmachine;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInListTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ApplicationTest extends NsTest implements Rollback {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomNumberInListTest(
            () -> {
                run("450", "[콜라,1500,20];[사이다,1000,10]", "3000", "콜라", "사이다");
                assertThat(output()).contains(
                    "자판기가 보유한 동전", "500원 - 0개", "100원 - 4개", "50원 - 1개", "10원 - 0개",
                    "투입 금액: 3000원", "투입 금액: 1500원"
                );
            },
            100, 100, 100, 100, 50
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(
            () -> {
                runException("-1");
                assertThat(output()).contains(ERROR_MESSAGE);
            }
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }

    @ParameterizedTest
    @MethodSource({"상품여러개_입력_예외테스트", "상품한개_입력_예외테스트", "구매_예외테스트", "자판기_보유금액_예외테스트"})
    void exception(List<String> args) {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class);
             final MockedStatic<Console> mockConsole = mockStatic(Console.class)) {

            mockRandoms.when(() -> Randoms.pickNumberInList(anyList())).thenReturn(100, 100, 100, 100, 50);
            mockConsole(args, mockConsole);

            assertThatThrownBy(() -> Application.main(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_MESSAGE);
        }
    }

    private void mockConsole(List<String> args, MockedStatic<Console> mockConsole) {
        mockConsole.when(Console::readLine).thenReturn(args.get(0), args.subList(1, args.size()).toArray());
    }


    private static Arguments toArguments(String... s) {
        return Arguments.of(Arrays.asList(s));
    }

    private static Stream<Arguments> 상품여러개_입력_예외테스트() {
        return Stream.of(
            toArguments("450", "콜라,20,1500;사이다,10,1000"),
            toArguments("450", "{콜라,20,1500};{사이다,10,1000}"),
            toArguments("450", "[콜라,20,1500];[사이다,10,1000"),
            toArguments("450", "[콜라,20,1500][사이다,10,1000]"),
            toArguments("450", "[콜라,20,1500],[사이다,10,1000]"),
            toArguments("450", "[콜라,20,1500];[사이다,10]"),
            toArguments("450", "[콜라,20,1500];[사이다,aa,1000]")
        );
    }

    private static Stream<Arguments> 상품한개_입력_예외테스트() {
        return Stream.of(
            toArguments("450", "콜라,20,1500"),
            toArguments("450", "{콜라,20,1500}"),
            toArguments("450", "[콜라;20;1500]"),

            toArguments("450", "[콜라,20,]"),
            toArguments("450", "[콜라,20,1500,10]"),

            toArguments("450", "[콜라,20,90]"),
            toArguments("450", "[콜라,20,101]"),

            toArguments("450", "[콜라,사이다,1500]"),
            toArguments("450", "[콜라,20,사이다]")
        );
    }

    private static Stream<Arguments> 구매_예외테스트() {
        return Stream.of(
            // 등록되지 않은 상품명
            toArguments("450", "[콜라,20,1500];[사이다,10,1000]", "3000", "밀키스"),
            // 물품 수량 없음
            toArguments("450", "[콜라,1,1500];[사이다,10,1000]", "3000", "콜라", "콜라"),
            // 투입한 금액 부족
            toArguments("450", "[콜라,20,1500];[사이다,10,1000]", "1100", "콜라", "콜라")
        );
    }

    private static Stream<Arguments> 자판기_보유금액_예외테스트() {
        return Stream.of(
            toArguments("9"),
            toArguments("1001")
        );
    }
}
