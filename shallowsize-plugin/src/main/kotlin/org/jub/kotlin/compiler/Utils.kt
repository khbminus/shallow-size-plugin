package org.jub.kotlin.compiler

import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.*
import org.jetbrains.kotlin.config.CompilerConfiguration
import java.io.File
import java.io.PrintStream

internal var messageCollector: MessageCollector? = null
internal const val PLUGIN_ID = "org.jub.kotlin.compiler"

internal fun CompilerConfiguration.initMessageCollector(filePath: String) {
    val file = File(filePath)
    file.createNewFile()
    this.put(
        CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY,
        PrintingMessageCollector(PrintStream(file.outputStream()), MessageRenderer.PLAIN_FULL_PATHS, true),
    )
    printConfiguration()
}

internal fun MessageCollector.log(message: String) {
    this.report(
        CompilerMessageSeverity.LOGGING,
        "ShallowSize: $message",
        CompilerMessageLocation.create(null),
    )
}

internal fun CompilerConfiguration.printConfiguration() {
    val messageCollector = this[CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY]
    messageCollector?.log("Shallow size plugin config")
    messageCollector?.log("")
    messageCollector?.log("ENABLED: ${PluginConfig.enabled}")
    messageCollector?.log("Excluded:")
    PluginConfig.excludedClasses.forEach {
        messageCollector?.log(" - $it")
    }
    messageCollector?.log("")
}
