# Kotlin_SurFun_TodoList

## Why
이 프로젝트는 코틀린 언어를 학습하고, 기본적인 안드로이드 개발 프로젝트 구조와 기능 구현을 연습하기 위해 시작되었습니다. 간단한 TodoList 애플리케이션을 통해 코틀린의 주요 기능과 안드로이드 개발의 기본을 배우는 것이 목표입니다.

## What
가장 가벼운 TodoList 애플리케이션을 구현하여 코틀린 언어와 안드로이드 개발 환경에 익숙해집니다. 주요 기능은 다음과 같습니다:
- **Add Task**: 새로운 할 일을 추가합니다.
- **Delete Task**: 기존 할 일을 삭제합니다.
- **Update Task**: 할 일의 내용을 업데이트합니다.
- **CheckBox**: 할 일 완료 상태를 체크박스로 표시합니다.

## How
### 기능 구현
1. **Add Task**
   - `EditText`를 통해 사용자가 할 일 내용을 입력하고, `Button`을 클릭하면 새로운 할 일을 목록에 추가합니다.
   - `RecyclerView`를 사용하여 할 일 목록을 표시합니다.
   
2. **Delete Task**
   - `RecyclerView` 항목을 길게 누르면 삭제 옵션이 나타나고, 선택하면 해당 항목이 삭제됩니다.
   
3. **Update Task**
   - `RecyclerView` 항목을 길게 누르면 업데이트 옵션이 나타나고, 선택하면 할 일 내용을 수정할 수 있는 대화상자가 나타납니다.
   
4. **CheckBox**
   - 각 할 일 항목 옆에 `CheckBox`를 추가하여 완료 상태를 표시합니다.
   - `CheckBox` 상태에 따라 할 일 텍스트의 스타일(취소선, 색상 등)이 변경됩니다.
   - ![image](https://github.com/user-attachments/assets/c3ff0ee4-5650-4c34-88e5-24a9682e2ea9)


### Trouble Shooting
#### Layout 충돌 문제
- **문제**: `ListView`를 사용할 때, `CheckBox`와 같은 포커스 가능한 뷰가 길게 누르기 이벤트를 방해하는 문제가 발생했습니다.
- **해결 방법**: `RecyclerView`로 전환하고, `ViewHolder` 패턴을 적용하여 각 항목의 이벤트를 쉽게 처리할 수 있도록 했습니다. 또한, `RecyclerView`의 `descendantFocusability` 속성을 설정하여 포커스 문제를 해결했습니다.

#### RecyclerView 사용의 장점
- **성능 향상**: `RecyclerView`는 `ListView`보다 더 나은 성능과 유연성을 제공합니다.
- **유연한 이벤트 처리**: `ViewHolder` 패턴을 통해 각 항목의 클릭 및 길게 누르기 이벤트를 쉽게 처리할 수 있습니다.
- **복잡한 레이아웃 처리**: 다양한 항목 유형과 복잡한 레이아웃을 유연하게 처리할 수 있습니다.

## Conclusion
이 프로젝트를 통해 코틀린과 안드로이드 개발의 기본을 학습하였습니다. `RecyclerView`와 같은 더 나은 도구를 사용하여 성능과 유연성을 향상시킬 수 있음을 배웠습니다. 앞으로도 이와 같은 접근 방식을 통해 더 복잡한 애플리케이션을 개발하는 데 유용하게 사용할 수 있을 것입니다.
