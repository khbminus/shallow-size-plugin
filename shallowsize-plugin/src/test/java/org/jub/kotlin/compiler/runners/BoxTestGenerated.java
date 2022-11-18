

package org.jub.kotlin.compiler.runners;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jub.kotlin.compiler.GenerateTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler/code-gen/box")
@TestDataPath("$PROJECT_ROOT")
public class BoxTestGenerated extends AbstractBoxTest {
    @Test
    public void testAllFilesPresentInBox() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler/code-gen/box"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
    }

    @Test
    @TestMetadata("nested.kt")
    public void testNested() throws Exception {
        runTest("shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler/code-gen/box/nested.kt");
    }

    @Test
    @TestMetadata("shallowSizeExists.kt")
    public void testShallowSizeExists() throws Exception {
        runTest("shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler/code-gen/box/shallowSizeExists.kt");
    }

    @Test
    @TestMetadata("simple.kt")
    public void testSimple() throws Exception {
        runTest("shallowsize-plugin/src/test/resources/org/jub/kotlin/compiler/code-gen/box/simple.kt");
    }
}
