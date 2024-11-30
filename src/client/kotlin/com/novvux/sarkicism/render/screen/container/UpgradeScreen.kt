package com.novvux.sarkicism.render.screen.container

import com.mojang.blaze3d.systems.RenderSystem
import com.novvux.sarkicism.Sarkicism.Companion.MOD_ID
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.render.DiffuseLighting
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import org.joml.Quaternionf


/*
@Environment(EnvType.CLIENT)
class UpgradeScreen(player: PlayerEntity): AbstractInventoryScreen<PlayerScreenHandler>(player.playerScreenHandler, player.inventory, Text.translatable("container.crafting")), RecipeBookProvider {
    private var narrow: Boolean = false
    private var mouseDown: Boolean = false

    init {
        this.titleX = 97
    }

    override fun init() {
        super.init()
        narrow = width < 379
    }
}*/

@Environment(EnvType.CLIENT)
class UpgradeScreen(player: PlayerEntity): Screen(Text.translatable("container.crafting")) {
    val BACKGROUND_TEXTURE = Identifier.of(MOD_ID, "item/egor")
    val backgroundWidth: Int = 128
    val backgroundHeight: Int = 128
    var animationTime: Float = 0.0F

    override fun init() {
        val addButton = ButtonWidget.builder(Text.of("+")) { btn ->
            this.client?.setScreen(null)
        }.dimensions(this.width / 2 - 100, this.height / 2 - 20, 10, 10).build()
        this.addDrawableChild(addButton)

        //val containerWidget = UpgradeContainerWidget(460, 110, this.width - 920, this.height - 220, Text.of("Hello"))
        //this.addDrawableChild(containerWidget)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)
        RenderSystem.disableDepthTest()

        //this.renderInGameBackground(context)
        //this.drawBackground(context, delta, mouseX, mouseY)
        //context.drawTexture(BACKGROUND_TEXTURE, width, height, 0, 0, this.backgroundWidth, this.backgroundHeight)

        animationTime++
    }

    override fun renderBackground(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        this.renderInGameBackground(context)
        this.drawBackground(context, delta)
        //drawEntity(context, 0, 0, width, height, 30, 0.0625F, this.mouseX, this.mouseY, this.client?.player)
    }

    fun drawBackground(context: DrawContext, delta: Float) {
        val width = this.width
        val height = this.height
        //context.drawTexture(BACKGROUND_TEXTURE, 0, 0, 0, 0, this.backgroundWidth, this.backgroundHeight)
        drawEntity(context, 0, 0, width + 75, height + 78, 30, 0.0625F, this.client?.player)
    }

    fun drawEntity(context: DrawContext, x1: Int, y1: Int, x2: Int, y2: Int, size: Int, f: Float, entity: ClientPlayerEntity?) {
        if (entity == null) return
        val x = (x1 + x2) / 2.0F
        val y = (y1 + y2) / 2.0F
        context.enableScissor(x1, y1, x2, y2)

        val byaw = entity.bodyYaw
        val yaw = entity.yaw
        val pitch = entity.pitch
        val phyaw = entity.prevHeadYaw
        val hyaw = entity.headYaw

        entity.bodyYaw = animationTime
        entity.yaw = animationTime
        entity.pitch = 0F//-rotationY * 20.0F
        entity.headYaw = entity.yaw
        entity.prevHeadYaw = entity.yaw

        val scale = entity.scale
        val sizing = size.toFloat()/scale

        context.matrices.push()
        context.matrices.translate(x.toDouble(), y.toDouble(), 50.0)
        context.matrices.scale(sizing, sizing, -sizing)
        context.matrices.translate(0.0F, entity.height / 2.0F + f * scale, 0.0F)
        context.matrices.multiply(Quaternionf().rotateZ(Math.PI.toFloat()))
        client!!.entityRenderDispatcher.setRenderShadows(false)
        RenderSystem.runAsFancy {
            client!!.entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.matrices, context.vertexConsumers, 15728880)
        }
        client!!.entityRenderDispatcher.setRenderShadows(true)
        context.draw()
        context.matrices.pop()
        DiffuseLighting.enableGuiDepthLighting()

        entity.bodyYaw = byaw
        entity.yaw = yaw
        entity.pitch = pitch
        entity.prevHeadYaw = phyaw
        entity.headYaw = hyaw

        context.disableScissor()
    }

    override fun shouldPause(): Boolean {
        return false
    }
}


    /*
    fun drawEntity(context: DrawContext, x1: Int, y1: Int, x2: Int, y2: Int, size: Int, f: Float, mouseX: Int, mouseY: Int, entity: ClientPlayerEntity?) {
        if (entity == null) return
        val x = (x1 + x2) / 2.0F
        val y = (y1 + y2) / 2.0F
        context.enableScissor(x1, y1, x2, y2)

        val rotationX = atan(((x - mouseX) / 40.0F).toDouble()).toFloat()
        //val rotationY = atan(((h - mouseY) / 40.0F).toDouble()).toFloat()

        val rotationZQuat = Quaternionf().rotateZ(Math.PI.toFloat())
        //val rorationXQuat = Quaternionf().rotateX(rotationY * 20.0F * (Math.PI / 180F).toFloat())
        //rotationZQuat.mul(rorationXQuat)

        val byaw = entity.bodyYaw
        val yaw = entity.yaw
        val pitch = entity.pitch
        val phyaw = entity.prevHeadYaw
        val hyaw = entity.headYaw

        entity.bodyYaw = 180.0F + rotationX * 20.0F
        entity.yaw = 180.0F + rotationX * 40.0F
        entity.pitch = 0F//-rotationY * 20.0F
        entity.headYaw = entity.yaw
        entity.prevHeadYaw = entity.yaw

        val scale = entity.scale
        val sizing = size.toFloat()/scale
        drawEntity(context, x, y, sizing, Vector3f(0.0F, entity.height / 2.0F + f * scale, 0.0F), rotationZQuat, entity)

        entity.bodyYaw = byaw
        entity.yaw = yaw
        entity.pitch = pitch
        entity.prevHeadYaw = phyaw
        entity.headYaw = hyaw

        context.disableScissor()
    }

    fun drawEntity(context: DrawContext, x: Float, y: Float, size: Float, vector3f: Vector3f, quaternionf: Quaternionf, entity: LivingEntity) {
        context.matrices.push()
        context.matrices.translate(x.toDouble(), y.toDouble(), 50.0)
        context.matrices.scale(size, size, -size)
        context.matrices.translate(vector3f.x, vector3f.y, vector3f.z)
        context.matrices.multiply(quaternionf)
        DiffuseLighting.method_34742()
        val entityRenderDispatcher = MinecraftClient.getInstance().entityRenderDispatcher
        //quaternionf2.let { entityRenderDispatcher.rotation = it.conjugate(Quaternionf()).rotateY(Math.PI.toFloat()) }
        entityRenderDispatcher.setRenderShadows(false)
        RenderSystem.runAsFancy {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, context.matrices, context.vertexConsumers, 15728880)
        }
        context.draw()
        entityRenderDispatcher.setRenderShadows(true)
        context.matrices.pop()
        DiffuseLighting.enableGuiDepthLighting()
    }

    override fun shouldPause(): Boolean {
        return false;
    }
}


     */