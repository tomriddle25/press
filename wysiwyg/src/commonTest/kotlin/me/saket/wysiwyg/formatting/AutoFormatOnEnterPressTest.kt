package me.saket.wysiwyg.formatting

import kotlin.test.Test

class AutoFormatOnEnterPressTest : BaseApplyMarkdownSyntaxTest() {

  @Test fun `enter after fenced code syntax`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |Alfred: Shall you be taking the Batpod sir?
                |```▮
                |Batman/Bruce Wayne: In the middle of the day Alfred?
                """.trimMargin(),
        output = """
                |Alfred: Shall you be taking the Batpod sir?
                |```
                |▮
                |```
                |Batman/Bruce Wayne: In the middle of the day Alfred?
                """.trimMargin()
    )
  }

  @Test fun `enter after fenced code syntax and language name`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |Alfred: Shall you be taking the Batpod sir?
                |```kotlin▮
                |Batman/Bruce Wayne: In the middle of the day Alfred?
                """.trimMargin(),
        output = """
                |Alfred: Shall you be taking the Batpod sir?
                |```kotlin
                |▮
                |```
                |Batman/Bruce Wayne: In the middle of the day Alfred?
                """.trimMargin()
    )
  }

  @Test fun `enter after fenced code syntax when already inside a fenced code block`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |```
                |fun someCodeBlock() {}
                |```
                |
                |Alfred: Shall you be taking the Batpod sir?
                |
                |```kotlin▮
                |```
                |Batman/Bruce Wayne: In the middle of the day Alfred?
                |
                |```javaOmg
                |fun anotherCodeBlock() {}
                |```
                """.trimMargin(),
        output = null
    )
  }

  @Test fun `enter key after a valid unordered list item`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |- Milk
                |- Bread▮
                """.trimMargin(),
        output = """
                |# Shopping list
                |- Milk
                |- Bread
                |- ▮
                """.trimMargin()
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |+ Milk
                |+ Bread▮
                """.trimMargin(),
        output = """
                |# Shopping list
                |+ Milk
                |+ Bread
                |+ ▮
                """.trimMargin()
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |* Milk
                |* Bread▮
                """.trimMargin(),
        output = """
                |# Shopping list
                |* Milk
                |* Bread
                |* ▮
                """.trimMargin()
    )
  }

  @Test fun `enter key after an invalid unordered list item`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |- Milk
                |Bread▮
                """.trimMargin(),
        output = null
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |+ Milk
                |+Bread▮
                """.trimMargin(),
        output = null
    )
  }

  @Test fun `enter key after a valid ordered list item`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |1. Milk
                |2. Bread▮
                """.trimMargin(),
        output = """
                |# Shopping list
                |1. Milk
                |2. Bread
                |3. ▮
                """.trimMargin()
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |199. Milk
                |200. Bread▮
                """.trimMargin(),
        output = """
                |# Shopping list
                |199. Milk
                |200. Bread
                |201. ▮
                """.trimMargin()
    )
  }

  @Test fun `enter key after an invalid ordered list item`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |1. Milk
                |2.Bread▮
                """.trimMargin(),
        output = null
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |1. Milk
                |2Bread▮
                """.trimMargin(),
        output = null
    )
  }

  @Test fun `enter key on an empty unordered list item`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |- Milk
                |- Bread
                |- ▮
                |
                |Some other text
                """.trimMargin(),
        output = """
                |# Shopping list
                |- Milk
                |- Bread
                |
                |▮
                |
                |Some other text
                """.trimMargin()
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |+ Milk
                |+ Bread
                |+ ▮
                |
                |Some other text
                """.trimMargin(),
        output = """
                |# Shopping list
                |+ Milk
                |+ Bread
                |
                |▮
                |
                |Some other text
                """.trimMargin()
    )

    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |* Milk
                |* Bread
                |* ▮
                |
                |Some other text
                """.trimMargin(),
        output = """
                |# Shopping list
                |* Milk
                |* Bread
                |
                |▮
                |
                |Some other text
                """.trimMargin()
    )
  }

  @Test fun `enter key on an empty ordered list item`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |# Shopping list
                |1. Milk
                |2. Bread
                |3. ▮
                |
                |Some other text
                """.trimMargin(),
        output = """
                |# Shopping list
                |1. Milk
                |2. Bread
                |
                |▮
                |
                |Some other text
                """.trimMargin()
    )
  }

  @Test fun `enter key on an empty paragraph`() {
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |▮
                """.trimMargin(),
        output = null
    )
    AutoFormatOnEnterPress.onEnterTest(
        input = """
                |
                |▮
                """.trimMargin(),
        output = null
    )
  }
}
