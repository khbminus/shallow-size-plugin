package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.descriptors.EffectiveVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.builder.buildSimpleFunction
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.declarations.utils.isData
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.name.*
import org.jub.kotlin.compiler.log
import org.jub.kotlin.compiler.messageCollector

class ShallowSizeGenerator(session: FirSession) : FirDeclarationGenerationExtension(session) {
    object Key : GeneratedDeclarationKey() {
        override fun toString(): String = "ShallowSizeGeneratorKey"
    }

    override fun generateFunctions(
        callableId: CallableId,
        context: MemberGenerationContext?
    ): List<FirNamedFunctionSymbol> {
        val owner = context?.owner
        require(owner is FirRegularClassSymbol)

        messageCollector?.log("Generating function with name=${callableId.callableName}, origin=${Key.origin}, owner=${owner.name}")
        return listOf(
            buildSimpleFunction {
                resolvePhase = FirResolvePhase.BODY_RESOLVE
                moduleData = session.moduleData
                origin = Key.origin
                status = FirResolvedDeclarationStatusImpl(
                    Visibilities.Public,
                    Modality.FINAL,
                    EffectiveVisibility.Public
                )
                returnTypeRef = session.builtinTypes.intType
                name = callableId.callableName
                dispatchReceiverType = callableId.classId?.toConeType()
                symbol = FirNamedFunctionSymbol(callableId)
            }.symbol
        )
    }

    private fun ClassId.toConeType(typeArguments: Array<ConeTypeProjection> = emptyArray()) =
        ConeClassLikeTypeImpl(ConeClassLikeLookupTagImpl(this), typeArguments, isNullable = false)

    override fun getCallableNamesForClass(classSymbol: FirClassSymbol<*>) = if (classSymbol.isData) {
        setOf(FOO_ID.callableName, SpecialNames.INIT)
    } else {
        emptySet()
    }

    override fun getTopLevelClassIds() = setOf<ClassId>()
    companion object {
        val MY_CLASS_ID = ClassId(FqName.fromSegments(listOf("foo", "bar")), Name.identifier("Aboba"))
        val FOO_ID = CallableId(MY_CLASS_ID, Name.identifier("shallowSize"))
    }
}
