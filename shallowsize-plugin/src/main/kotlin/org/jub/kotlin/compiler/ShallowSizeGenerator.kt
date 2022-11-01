package org.jub.kotlin.compiler

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.descriptors.EffectiveVisibility
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.builder.buildSimpleFunction
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension
import org.jetbrains.kotlin.fir.extensions.MemberGenerationContext
import org.jetbrains.kotlin.fir.moduleData
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.fir.types.ConeTypeProjection
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl
import org.jetbrains.kotlin.name.*

class ShallowSizeGenerator(session: FirSession) : FirDeclarationGenerationExtension(session) {
    companion object {
        val MY_CLASS_ID =  ClassId(FqName.fromSegments(listOf("foo", "bar")), Name.identifier("Aboba"))
        val FOO_ID = CallableId(MY_CLASS_ID, Name.identifier("shallowSize"))
    }
    @OptIn(SymbolInternals::class)
    override fun generateFunctions(
        callableId: CallableId,
        context: MemberGenerationContext?
    ): List<FirNamedFunctionSymbol> {
        println("here")
        messageCollector?.log("Started FIR")
        val owner = context?.owner
        require(owner is FirRegularClassSymbol)
//        if (!owner.fir.isData) {
//            return emptyList()
//        }
        messageCollector?.log(owner.toString())
//        val matchedClassId = owner.fir.matchedClass ?: return emptyList()
//        val matchedClassSymbol = session.symbolProvider.getClassLikeSymbolByClassId(matchedClassId)
        return listOf(
            buildSimpleFunction {
                resolvePhase = FirResolvePhase.BODY_RESOLVE
                moduleData = session.moduleData
                origin = Key.origin
                name = callableId.callableName
                returnTypeRef = session.builtinTypes.intType
                status = FirResolvedDeclarationStatusImpl(
                    Visibilities.Public,
                    Modality.FINAL,
                    EffectiveVisibility.Public
                )
                dispatchReceiverType = callableId.classId?.toConeType()
                symbol = FirNamedFunctionSymbol(callableId)
            }.symbol
        )
    }

    private fun ClassId.toConeType(typeArguments: Array<ConeTypeProjection> = emptyArray()) =
        ConeClassLikeTypeImpl(ConeClassLikeLookupTagImpl(this), typeArguments, isNullable = false)


    override fun getCallableNamesForClass(classSymbol: FirClassSymbol<*>) = if (classSymbol.classId == MY_CLASS_ID) {
        setOf(FOO_ID.callableName, SpecialNames.INIT)
    } else {
        emptySet()
    }

    override fun getTopLevelClassIds() = setOf(MY_CLASS_ID)

    object Key : GeneratedDeclarationKey()
}
