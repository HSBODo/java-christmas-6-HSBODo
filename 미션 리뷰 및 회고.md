# 미션 - 크리스마스 프로모션
## 🚀 기능 요구 사항

> 이번 미션은 이메일 형식의 기능 요구 사항입니다. 문제를 구현하는 데 필요한 요구사항과 배경지식은 이메일 내용에 전부 담겨있으니, 꼼꼼하게 확인하고 필요하다면 주어진 문제의 내용을 통해 유추하고 스스로 판단해
> 구현해 주시면 됩니다. 문제의 모든 내용은 충분히 검토되었으며, 출제 의도를 담은 내용임을 알려드립니다.

보낸 사람: 비즈니스팀 \<`biz@woowacourse.io`\>  
받는 사람: 개발팀 \<`dev@woowacourse.io`\>

제목: 12월 이벤트를 위한 개발 요청

안녕하세요. 비즈니스팀입니다!

다가오는 2023년 12월에 우테코 식당에서 1년 중 제일 큰 이벤트를 개최하려고 합니다.  
12월을 위해 이벤트 예산을 넉넉히 확보해 두었으니, 예산은 걱정하지 마세요~

특별히 이번 12월 이벤트를 진행하기 위해서, 개발팀의 도움이 많이 필요합니다.  
아래 메뉴와 달력 이미지를 보면서 12월 이벤트 계획과 요청 내용을 본격적으로 설명해 드릴게요.

#### 메뉴

```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

#### 달력

![](image.png)

#### 이벤트 목표

1. 중복된 할인과 증정을 허용해서, 고객들이 혜택을 많이 받는다는 것을 체감할 수 있게 하는 것
2. 올해 12월에 지난 5년 중 최고의 판매 금액을 달성
3. 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것

#### 12월 이벤트 계획

- 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인  
      (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
- 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
- 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용

#### 혜택 금액에 따른 12월 이벤트 배지 부여

- 총혜택 금액에 따라 다른 이벤트 배지를 부여합니다. 이 배지는 2024 새해 이벤트에서 활용할 예정입니다.
  배지에 따라 새해 이벤트 참여 시, 각각 다른 새해 선물을 증정할 예정입니다.
    - 5천 원 이상: 별
    - 1만 원 이상: 트리
    - 2만 원 이상: 산타

#### 고객에게 안내할 이벤트 주의 사항

- 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
- 음료만 주문 시, 주문할 수 없습니다.
- 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.  
  (e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)

#### '12월 이벤트 플래너' 개발 요청 사항

- 고객들이 식당에 방문할 날짜와 메뉴를 미리 선택하면 이벤트 플래너가 주문 메뉴, 할인 전 총주문 금액, 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지 내용을 보여주기를 기대합니다.
- 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
    - 방문할 날짜는 1 이상 31 이하의 숫자로만 입력받아 주세요.
    - 1 이상 31 이하의 숫자가 아닌 경우, "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - 모든 에러 메시지는 "[ERROR]"로 시작하도록 작성해 주세요.
- 주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
    - 고객이 메뉴판에 없는 메뉴를 입력하는 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - 메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요. 이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - 메뉴 형식이 예시와 다른 경우, "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - 중복 메뉴를 입력한 경우(e.g. 시저샐러드-1,시저샐러드-1), "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여 주세요.
    - 모든 에러 메시지는 "[ERROR]"로 시작하도록 작성해 주세요.
- 주문 메뉴의 출력 순서는 자유롭게 출력해 주세요.
- 총혜택 금액에 따라 이벤트 배지의 이름을 다르게 보여 주세요.
- 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
- 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
- 증정 메뉴
    - 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"으로 보여 주세요.
- 혜택 내역
    - 고객에게 적용된 이벤트 내역만 보여 주세요.
    - 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 보여 주세요.
    - 혜택 내역에 여러 개의 이벤트가 적용된 경우, 출력 순서는 자유롭게 출력해주세요.
- 이벤트 배지
    - 이벤트 배지가 부여되지 않는 경우, "없음"으로 보여 주세요.
- 적용된 이벤트가 하나도 없는 경우는 아래 예시를 참고해 주세요.

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
26 
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
타파스-1,제로콜라-1 
12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
타파스 1개
제로콜라 1개

<할인 전 총주문 금액>
8,500원
 
<증정 메뉴>
없음
 
<혜택 내역>
없음
 
<총혜택 금액>
0원
 
<할인 후 예상 결제 금액>
8,500원
 
<12월 이벤트 배지>
없음
```

#### 기대하는 '12월 이벤트 플래너'의 예시 모습

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
```

기대하는 예시를 한 개만 들어서 설명했지만, 더 다양한 사례가 있을 것으로 예상됩니다.  
개발이 완료되는 대로 공유해 주시면, 비즈니스팀에서 1주일간 테스트를 진행하고 오픈할 예정입니다.  
1주일 뒤에 예정된 '12월 이벤트 플래너' 개발 회의에서 더 자세한 얘기를 해보면 좋겠습니다.

감사합니다. :)

---
## 🎯 프로그래밍 요구 사항

- JDK 17 버전에서 실행 가능해야 한다. **JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리한다.**
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- [Java 코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java) 가이드를 준수하며 프로그래밍한다.
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그램 구현이 완료되면 `ApplicationTest`의 모든 테스트가 성공해야 한다. **테스트가 실패할 경우 0점 처리한다.**
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    - 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- else 예약어를 쓰지 않는다.
    - 힌트: if 조건절에서 값을 return 하는 방식으로 구현하면 else를 사용하지 않아도 된다.
    - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

### 추가된 요구 사항

- 아래 있는 `InputView`, `OutputView` 클래스를 참고하여 입출력 클래스를 구현한다.
    - 입력과 출력을 담당하는 클래스를 별도로 구현한다.
    - 해당 클래스의 패키지, 클래스명, 메서드의 반환 타입과 시그니처는 자유롭게 구현할 수 있다.
  ```java
  public class InputView {
      public int readDate() {
          System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
          String input = Console.readLine();    
          // ...
      }
      // ...
  }
  ```
  ```java
  public class OutputView {
      public void printMenu() {
          System.out.println("<주문 메뉴>");
          // ...
      }
      // ...
  }
  ```

### 라이브러리

- `camp.nextstep.edu.missionutils`에서 제공하는 `Console` API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

---
# 요구사항 구현 및 회고
## 🚨️ 필요기능 분석 및 목록 정의
* 고객에게 방문 날짜를 입력 받는다.
* 입력받은 날짜를 검증한다
    * 입력값이 숫자인지 확인한다.
    * 1이상 31이하의 범위에 숫자인지 확인한다.
    * 검증에 실패하면 **"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."** 에러메시지를 보여주고 다시 입력받도록 한다.
* 고객에게 주문하실 메뉴와 개수를 입력받는다.
    * 입력값의 포맷은 (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)로 한다.
* 입력받은 메뉴와 개수를 검증한다.
    * 입력 포맷이 맞는지 확인한다.
    * 메뉴판에 있는 메뉴인지 확인한다.
    * 음료만 주문 시, 주문할 수 없습니다.
    * 메뉴의 개수가 1이상의 숫자인지 확인한다.
    * 메뉴를 중복해서 입력했는지 확인한다.
    * 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
    * 검증에 실패하면 **"[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."** 에러메시지를 보여주고 다시 입력받도록 한다.
* 고객에게 입력받은 날짜를 기반으로 크리스마스 디데이 이벤트 할인금액을 계산한다.
    * 이벤트 기간은 2023.12.1 ~ 2023.12.25
    * 이벤트 시작기간 2023.12.1을 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가하고 총주문 금액에서 해당 금액만큼 할인한다.
    * (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인) 
* 고객에게 입력받은 날짜를 기반으로 평일 할인 이벤트 할인금액을 계산한다.
    * 이벤트 기간은 2023.12.1 ~ 2023.12.31 
    * 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    * 이벤트 조건은 일요일~목요일에 예약한 고객
    * 디저트 메뉴 1개당 2,023원 할인
* 고객에게 입력받은 날짜를 기반으로 주말 할인 이벤트 할인금액을 계산한다.
    * 이벤트 기간은 2023.12.1 ~ 2023.12.31 
    * 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    * 이벤트 조건은 금요일, 토요일에 예약한 고객
    * 메인 메뉴 1개당 2,023원 할인
* 고객에게 입력받은 날짜를 기반으로 특별 할인 이벤트 할인금액을 계산한다.
    * 이벤트 기간은 2023.12.1 ~ 2023.12.31
    * 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    * 이벤트 이벤트 조건은 12월 3,10,17,24,25,31일에 예약한 고객
    * 총주문 금액에서 1,000원 할인
*  할인 전 총주문 금액을 기준으로 증정이벤트를 진행한다.
    * 이벤트 기간은 2023.12.1 ~ 2023.12.31
    * 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    * 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.
* 혜택 금액을 기준으로 배지를 부여한다.
    * 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
    * 혜택 금액이 5천 원 이상: 별
    * 혜택 금액이 1만 원 이상: 트리
    * 혜택 금액이 2만 원 이상: 산타
## 📮 기능 구현
####  MVC 디자인 패턴을 적용하여 클래스(객체)를 분리하고, 도메인 로직에 집중하는 방향으로 구현해보자.

### MVC 디자인 패턴의 큰 그림 그리기
![img_1.png](img_1.png)

---
#### View
뷰 객체는 컨트롤러에서 받은 모델 객체의 데이터를 기반으로 화면을 보여주는 역할을 하도록 구현하였습니다.
```java
public class OutputView {

  private final String ORDER_MENU_TITLE = "<주문 메뉴>";
  private final String TOTAL_PRICE_BEFORE_DISCOUNT_TITLE = "<할인 전 총주문 금액>";
  private final String GIVEAWAY_TITLE = "<증정 메뉴>";
  private final String BENEFITS_DETAILS_TITLE = "<혜택 내역>";
  private final String TOTAL_BENEFITS_PRICE_TITLE = "<총혜택 금액>";
  private final String TOTAL_PRICE_AFTER_DISCOUNT_TITLE = "<할인 후 예상 결제 금액>";
  private final String BADGE_TITLE = "<12월 이벤트 배지>";
  private final String UNIT = "개";
  private final String WON = "원";
  private final String EMPTY = "없음";

  public void firstGreeting(Model model) {
    System.out.println(model.getModel().get("firstGreeting"));
  }

  public void eventBenefitsPreview(Model model) {
    System.out.println(model.getModel().get("preview"));
    System.out.println();
  }
  ...
}
```

---
#### Model
모델 객체는 데이터를 담는 역할을 하도록 구현하였습니다.
```java
public class Model {
    private Map<Object,Object> model;

    public Model() {
        this.model = new HashMap<>();
    }

    public void addAttribute(Object key, Object value){
        model.put(key,value);
    }

    public Map<Object, Object> getModel() {
        return model;
    }
}
```
addAttribute() 메서드를 구현하여 모델 객체에 데이터를 담을 수 있고,
getModel() 메서드를 통해 모델의 데이터를 가져 올 수 있습니다.

---
#### Controller
컨트롤러 객체는 중간의 중계자 역할을 하도록 구현하였습니다.  
  

중간에서 중계자 역할을 하기 위해서는 필요한 객체를 의존하고 있어야합니다. 그렇기 때문에 생성자 주입을 통하여 의존성을 주입하였습니다.
```java
public class ChristmasPromotionController {

  private InputView inputView;
  private OutputView outputView;
  private ValidationService validationService;
  private EventService eventService;

  public ChristmasPromotionController() {
    this.inputView = new InputView();
    this.outputView = new OutputView();
    this.validationService = new ValidationServiceImpl();
    this.eventService = new EventServiceImpl();
  }
}
```

---
#### DTO(Data Transfer Object)
엔티티와 DTO는 엄연히 서로 다른 관심사를 가지고 있고, 그렇기 때문에 분리하여 사용하기로 하였습니다.
DTO 객체의 관심사는 계층간에 데이터를 전달하는 역할을 하도록 구현하였다.  

DTO의 정의는 **" DTO는 어떠한 비즈니스 로직을 가져서는 안되며, 저장, 검색, 직렬화, 역직렬화 로직만을 가져야 한다고 한다."** 라고 한다.
```java
public class ReservationInfoDto {
    private int reservationDay;
    private Map<Menu, Integer> reservationMenusQuantity;
    private int totalPriceBeforeDiscount;
    private int totalDiscountPrice;
    private Map<BenefitsTitle, Integer> benefitsDetails;
    private Map<String, Integer> giveaway;
    private String badge;

    public ReservationInfoDto(String reservationDay, String reservationMenuAndQuantity) {
      ...
    }

    private void initReservationMenusQuantity(String reservationMenuAndQuantity) {
      ...
    }

    private void initTotalPriceBeforeDiscount() {
      ...
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public int getQuantityOf(String menuName) {
        return reservationMenusQuantity.get(Menu.getMenu(menuName));
    }

    public int getReservationDay() {
        return reservationDay;
    }

    public int getTotalPriceAfterDiscount() {
        return totalPriceBeforeDiscount - totalDiscountPrice;
    }

    public int getTotalDiscountPrice() {
        return totalDiscountPrice;
    }

    public String getBadge() {
        return badge;
    }

    public Map<String, Integer> getGiveaway() {
        return giveaway;
    }

    public Map<Menu, Integer> getReservationMenusQuantity() {
        return reservationMenusQuantity;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void applyGiveaway(BenefitsTitle discountTitle, Menu menu, int quantity) {
      ...
    }

    public void applyDiscountPrice(BenefitsTitle discountTitle, int discountPrice) {
      ...
    }

    public ReservationInfo toEntity() {
        return new ReservationInfo(
                reservationDay,
                reservationMenusQuantity,
                thousandUnitsComma(totalPriceBeforeDiscount),
                thousandUnitsComma(getTotalPriceAfterDiscount()),
                thousandUnitsComma(totalDiscountPrice),
                thousandUnitsComma(getTotalBenefitsPrice()),
                benefitsPriceOfBenefitsDetailsConvertThousandUnits(benefitsDetails),
                giveaway,
                badge
        );
    }

    private String thousandUnitsComma(int number) {
      ...
    }

    private Map<BenefitsTitle, String> benefitsPriceOfBenefitsDetailsConvertThousandUnits(Map<BenefitsTitle, Integer> benefitsDetails) {
      ...
    }

    public int getTotalBenefitsPrice() {
      ...
    }
}
```
toEntity 메서드를 구현하여 Entity를 생성하여 도메인 객체의 데이터를 보호하기 위해서 구현하였습니다.

---
#### Domain(Entity)
엔티티는 핵심 비지니스 로직을 담는 비지니스 도메인의 영역의 일부이다.
DTO 데이터를 기반으로 생성된 Entity로 Model객체를 초기화하는 역할을 하도록 구현하였습니다.

```java
public class ReservationInfo {
    private int reservationDay;
    private Map<Menu, Integer> reservationMenusQuantity;
    private String totalPriceBeforeDiscount;
    private String totalPriceAfterDiscount;
    private String totalDiscountPrice;
    private String totalBenefitsPrice;
    private Map<BenefitsTitle, String> benefitsDetails;
    private Map<String, Integer> giveaway;
    private String badge;


    public ReservationInfo(int reservationDay, Map<Menu, Integer> reservationMenusQuantity, String totalPriceBeforeDiscount, String totalPriceAfterDiscount, String totalDiscountPrice, String totalBenefitsPrice, Map<BenefitsTitle, String> benefitsDetails, Map<String, Integer> giveaway, String badge) {
        this.reservationDay = reservationDay;
        this.reservationMenusQuantity = reservationMenusQuantity;
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.totalPriceAfterDiscount = totalPriceAfterDiscount;
        this.totalDiscountPrice = totalDiscountPrice;
        this.totalBenefitsPrice = totalBenefitsPrice;
        this.benefitsDetails = benefitsDetails;
        this.giveaway = giveaway;
        this.badge = badge;
    }

    public Model toEventBenefitsPreviewModel() {
        final String PREVIEW_MESSAGE = "12월 " + reservationDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
        Model model = new Model();
        model.addAttribute("preview", PREVIEW_MESSAGE);
        return model;
    }

    public Model toOrderMenuModel() {
        Model model = new Model();
        reservationMenusQuantity.forEach((menu, quantity) -> {
            model.addAttribute(menu.getName(), quantity);
        });
        return model;
    }

    public Model toTotalPriceBeforeDiscountModel() {
        Model model = new Model();
        model.addAttribute("totalPriceBeforeDiscount", totalPriceBeforeDiscount);
        return model;
    }

    public Model toGiveawayModel() {
        Model model = new Model();
        giveaway.forEach((giveaway, quantity) -> {
            model.addAttribute("giveaway", giveaway);
            model.addAttribute("quantity", quantity);
        });
        return model;
    }

    public Model toBenefitsDetailsModel() {
        Model model = new Model();
        benefitsDetails.forEach((benefitsTitle, benefitsPrice) -> {
            model.addAttribute(benefitsTitle.getTitle(), benefitsPrice);
        });
        return model;
    }

    public Model toTotalBenefitsPriceModel() {
        Model model = new Model();
        model.addAttribute("totalBenefitsPrice", totalBenefitsPrice);
        return model;
    }

    public Model toTotalPriceAfterDiscountModel() {
        Model model = new Model();
        model.addAttribute("totalPriceAfterDiscount", totalPriceAfterDiscount);
        return model;
    }

    public Model toBadgeModel() {
        Model model = new Model();
        model.addAttribute("badge", badge);
        return model;
    }

}
```
Entity는 핵심 비지니스 로직을 담당하기 때문에 데이터가 변경되면 위험하다.  
그렇기 때문에 getter/setter를 사용하지 않아 외부에서 데이터 접근을 막아 데이터를 보호하고 캡슐화 하였습니다.  
객체에게 메세지를 던지도록 하여 객체스럽게 사용하도록 하였습니다.

---
### MVC 디자인 패턴을 사용한 이유는?
MVC 패턴을 사용하는 이유는 **"서로 분리되어 각자의 역할에 집중할 수 있게끔 개발을 하고 애플리케이션을 만든다면, 유지보수성과 애플리케이션의 확장성 그리고 유연성이 증가하고 코드를 재사용함으로써 중복 코딩이라는 문제점 또한 사라지게 된다."** 이라고 한다.  

하지만 막상 마음에는 확 와닫지 않았다.  
분리?, 역할에 집중?, 유지보수성?  
이러한 것을 이해하지 못하고 그냥 최대한 객체들의 역할을 분리하는데 집중하며 개발하였습니다.  
개발을 끝내고 최종적으로 테스트를 진행했을 때 증정이벤트의 값이 예상과 다르게 나오는 것을 확인하였습니다.  
그리고 생각하였습니다.  
증정이벤트에서 데이터가 이상하네? 그러면 증정이벤트를 핸들링하는 객체는  EventService잖아 EventService에 가보자  
그리고 데이터를 핸들링하는 객체는 ReservationDto잖아 가보자

어!?!?  

여기에 이러한 조건이 빠져있어서 데이터가 잘못 저장되었구나!!!  

테스트가 실패하고 오류가 난 부분을 발견하고 수정하는 시간이 총 10분도 안걸렸습니다.

이때 분리, 역할에 집중, 유지보수성 이라는 단어가 어떤 의미를 하는지 깨달았습니다.  
이것 때문에 MVC 디자인 패턴을 사용하고 역할을 분리하는 것인지 이해하였습니다.  
하지만 개발을 하다보면 역할이 명확한 경우도 있지만 애매한 경우도 많이 생기곤 합니다.  
예를 들어 이벤트를 서비스에 구현하는게 맞을까? 이벤트 객체를 따로 구현하여 관리할까? 등...  
이러한 부분은 많은 경험을 통해 배워야 한다고 생각합니다.  
그렇기 때문에 앞으로 많은 경험을 하며 역할을 분리하는 능력을 학습 할 것입니다.

---
### 인터페이스는 왜 사용할까?
```java
public interface EventService {
    public ReservationInfoDto applyDecemberEvent(ReservationInfoDto reservationInfoDto);

}
```
EventService를 인터페이스를 활용하여 구현하였습니다.  
이번 미션에서는 인터페이스를 활용하지 않아도 되지만 학습을 위해 활용하였습니다.  
인터페이스를 활용하여 틀을 잡아 놓을 수 있어 개발 시적전에 추상화하여 생각해 볼 수 있었습니다.  
만약 1월부터 12월까지 이벤트가 있었다면 이벤트들을 추상화하여 생각해 보고 구현 할 수 있었을 것입니다.  
```java
public interface EventService {
    public ReservationInfoDto applyDecemberEvent(ReservationInfoDto reservationInfoDto);
    public ReservationInfoDto applyJanuaryEvent(ReservationInfoDto reservationInfoDto);
    public ReservationInfoDto applyFebruaryEvent(ReservationInfoDto reservationInfoDto);
    public ReservationInfoDto applyMarchEvent(ReservationInfoDto reservationInfoDto);
    ...
}
```
이벤트 내용이 변경된다고하면 인터페이스의 다형성과 유연성 특징 때문에으로 변경된 내용의 인터페이스를 구현하여 교환할수 있습니다.  
단위 테스트를 할 때도 구현체를 교환해가며 테스트를 할 수 있었습니다.  

---
### 추상클래스는 왜 사용할까?
이번 미션의 요구사항중 할인 이벤트가 있습니다.  
크리스마스 디데이 할인, 평일 할인, 주말 할인, 특별 할인, 증정 이벤트, 배지 이벤트 등...  
이러한 여러 이벤트의 공통 요소인 이벤트 제목, 이벤트적용하기를 중복 멤버를 통합하여 공통 멤버로 추상화 하였습니다.
```java
public abstract class Event {
    public BenefitsTitle benefitsTitle;

    public abstract void apply(ReservationInfoDto reservationInfoDto);
}
```
```java
public class ChristmasDdayDiscount extends Event {
    private final int ONE_DAY_PER_DISCOUNT_PRICE;
    private int minDiscountPrice;
  
    public ChristmasDdayDiscount() {
        this.benefitsTitle = BenefitsTitle.크리스마스_디데이_할인;
        this.ONE_DAY_PER_DISCOUNT_PRICE = 100;
        this.minDiscountPrice = 1000;
    }
  
    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        int reservationDay = reservationInfoDto.getReservationDay();
        int discountPrice = minDiscountPrice + ((reservationDay - 1) * ONE_DAY_PER_DISCOUNT_PRICE);
        reservationInfoDto.applyDiscountPrice(benefitsTitle, discountPrice);
    }
}
```
Event 추상클래스를 상속받아 공통 멤버를 구현하였습니다.  
다형성을 활용하여 apply() 메서드가 각각 할인에 맞게 다른 내용으로 구현하였습니다.

```java
public class ThanksgivingDayDiscount extends Event {
    private final int ONE_DAY_PER_DISCOUNT_PRICE;
    private int minDiscountPrice;
  
    public ChristmasDdayDiscount() {
        this.benefitsTitle = BenefitsTitle.추석_할인;
        this.ONE_DAY_PER_DISCOUNT_PRICE = 100;
        this.minDiscountPrice = 1000;
    }
  
    @Override
    public void apply(ReservationInfoDto reservationInfoDto) {
        추석할인 적용 내용 ...
    }
}

```
다른 이벤트가 추가되어도 Event를 상속받아 apply() 메서드를 이벤트에 맞게 구현 하여 확장 할 수 있었습니다.

---
### TDD가 무조건 맞는 개발 방식인가?
3주차 미션을 하고 4주차 미션에서 TDD개발 방식으로 개발해보자고 목표하였다.  
아직 TDD 개발 방법론에 대해 깊게 공부하지 못한 상태였지만,  
우선 테스트코드 먼저 작성하고 그걸 기반으로 기능을 구현하자고 생각하여 진행햐였다.
그렇게 개발을 하다가 실제 코드의 예외를 테스트 코드에서 구현해야하는 어려움이 생겨서 구조를 변경하거나 우회하는 방법을
찾는 시간이 꽤나 걸렸고, 개발속도가 앞으로 나아가지 않았다.  
차라리 구현코드를 먼저 설계하고 테스트 코드로 검증하는 방법이 더욱 빠르지 않을까 하는 생각이 계속 들었다.  
TDD는 최종 결과로 가는 방법중 하나일 뿐이다.
TDD 개발 방법론이 맞다고 고집하다가는 우리의 목표의 최종 결과로 도달하지 못 할 수도 있다.  
TDD 개발 방법론을 고집하지 않고, TDD 개발 방법론을 더욱 깊이 공부하여 필요한 상황에 적절하게 적용하고 싶다.

## ✏️ 학습내용
* MVC 다자인 패턴의 사용 이유
* 관심사와 역할 객체 분리 
* 인터페이스 사용법
* 추상클래스 사용법
* TDD 개발방법만이 옳지 않다
















