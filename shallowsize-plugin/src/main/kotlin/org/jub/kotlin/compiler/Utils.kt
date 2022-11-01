package org.jub.kotlin.compiler

import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.*
import org.jetbrains.kotlin.config.CompilerConfiguration
import java.io.File
import java.io.PrintStream

internal var messageCollector: MessageCollector? = null

fun CompilerConfiguration.initMessageCollector(filePath: String) {
    val file = File(filePath)
    file.createNewFile()
    this.put(
        CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY,
        PrintingMessageCollector(PrintStream(file.outputStream()), MessageRenderer.PLAIN_FULL_PATHS, true),
    )
}

fun MessageCollector.log(message: String) {
    this.report(
        CompilerMessageSeverity.LOGGING,
        "ShallowSize: $message",
        CompilerMessageLocation.create(null),
    )
}
