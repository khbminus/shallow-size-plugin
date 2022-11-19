package org.jub.kotlin.compiler

import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey

private typealias CompilationKeyList<T> = CompilerConfigurationKey<List<T>>
@OptIn(ExperimentalCompilerApi::class)
class ShallowSizeCliProcessor : CommandLineProcessor {
    override val pluginId: String
        get() = PLUGIN_ID
    override val pluginOptions: Collection<AbstractCliOption>
        get() = listOf(ENABLED_OPTION, EXCLUDE_OPTION)

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) {
        when (option) {
            ENABLED_OPTION -> configuration.put(Keys.ENABLED, value.toBoolean())
            EXCLUDE_OPTION -> configuration.extendItem(Keys.EXCLUDED, value)
        }
    }

    private fun <T> CompilerConfiguration.extendItem(configurationKey: CompilationKeyList<T>, value: T) {
        val list = get(configurationKey) ?: emptyList()
        put(configurationKey, list + value)
    }

    companion object {
        internal val ENABLED_OPTION = CliOption(
            optionName = "enabled",
            valueDescription = "<true|false>",
            description = "Whether to enable the Shallow size plugin or not",
            allowMultipleOccurrences = false,
            required = false
        )
        internal val EXCLUDE_OPTION = CliOption(
            optionName = "exclude",
            valueDescription = "<fqname of data class>",
            description = "Exclude data class from adding shallowSize method",
            allowMultipleOccurrences = true,
            required = false
        )
    }
}
