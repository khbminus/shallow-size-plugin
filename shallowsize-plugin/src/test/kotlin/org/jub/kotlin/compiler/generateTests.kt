package org.jub.kotlin.compiler

import org.jetbrains.kotlin.generators.generateTestGroupSuiteWithJUnit5
import org.jub.kotlin.compiler.runners.AbstractBoxTest
import org.jub.kotlin.compiler.runners.AbstractFirBoxTest

private const val TEST_DATA_ROOT = "shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler"

fun main() {
    generateTestGroupSuiteWithJUnit5 {
        testGroup(
            testDataRoot = "$TEST_DATA_ROOT/code-gen/fir",
            testsRoot = "shallowsize-plugin/src/test/java"
        ) {
            testClass<AbstractFirBoxTest> {
                model("box")
            }
        }
    }
    generateTestGroupSuiteWithJUnit5 {
        testGroup(
            testDataRoot = "$TEST_DATA_ROOT/code-gen/ir",
            testsRoot = "shallowsize-plugin/src/test/java"
        ) {
            testClass<AbstractBoxTest> {
                model("box2")
            }
        }
    }
}
