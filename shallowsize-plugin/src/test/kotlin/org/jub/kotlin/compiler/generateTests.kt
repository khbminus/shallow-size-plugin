package org.jub.kotlin.compiler

import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5
import org.jub.kotlin.compiler.runners.AbstractBoxTest

private const val TEST_DATA_ROOT = "shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler"

fun main() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(
            testDataRoot = "$TEST_DATA_ROOT/code-gen",
            testsRoot = "shallowsize-plugin/src/test/java"
        ) {
            testClass<AbstractBoxTest> {
                model("box")
            }
        }
    }
}
