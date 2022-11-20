package org.jub.kotlin.compiler

import org.jetbrains.kotlin.config.CompilerConfigurationKey
import org.jub.kotlin.compiler.Names.PLUGIN_ID

internal object Keys {
    val ENABLED = CompilerConfigurationKey<Boolean>("$PLUGIN_ID.enabled")
    val EXCLUDED = CompilerConfigurationKey<List<String>>("$PLUGIN_ID.excluded")
}
