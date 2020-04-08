package com.github.ludmilaraskina.jackson

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.core.Version.unknownVersion
import com.fasterxml.jackson.databind.AnnotationIntrospector
import com.fasterxml.jackson.databind.introspect.Annotated
import com.fasterxml.jackson.databind.module.SimpleModule
import com.github.ludmilaraskina.annotation.Hidden
import com.github.ludmilaraskina.annotation.Masked
import com.github.ludmilaraskina.jackson.serializer.HiddenSerializer
import com.github.ludmilaraskina.jackson.serializer.MaskedSerializer

class LogModule : SimpleModule() {
    override fun setupModule(context: SetupContext) {
        super.setupModule(context)
        context.appendAnnotationIntrospector(LogAnnotationIntrospector())
    }
}

class LogAnnotationIntrospector : AnnotationIntrospector() {

    private val hiddenSerializer = HiddenSerializer()
    private val maskedSerializer = MaskedSerializer()

    override fun version(): Version {
        return unknownVersion()
    }

    override fun findSerializer(am: Annotated): Any? {
        return when {
            am.getAnnotation(Hidden::class.java) != null -> hiddenSerializer
            am.getAnnotation(Masked::class.java) != null -> maskedSerializer
            else -> null
        }
    }
}
