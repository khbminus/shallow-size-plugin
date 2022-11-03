package org.jub.kotlin.compiler

import org.jetbrains.kotlin.config.CompilerConfiguration

internal object PluginConfig {
    var enabled: Boolean = true
        private set
    var excludedClasses: Set<String> = emptySet()
        private set

    fun init(compilerConfiguration: CompilerConfiguration) {
        enabled = compilerConfiguration.get(Keys.ENABLED) ?: true
        excludedClasses = compilerConfiguration.get(Keys.EXCLUDED)?.toSet() ?: emptySet()
    }
}
