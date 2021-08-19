package io.github.fallOut015.witchcraft.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import io.github.fallOut015.witchcraft.world.level.block.entity.CrystalBlockEntity;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.system.MathUtil;

public class CrystalRenderer implements BlockEntityRenderer<CrystalBlockEntity> {
    private static final ResourceLocation CRYSTAL_BORDER_LOCATION = new ResourceLocation("witchcraft", "textures/block/crystal_border.png");

    public CrystalRenderer(Context context) {

    }

    @Override
    public boolean shouldRenderOffScreen(CrystalBlockEntity p_112306_) {
        return true;
    }
    @Override
    public void render(CrystalBlockEntity crystalBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        if(crystalBlockEntity.isActivated()) {
            System.out.println("Rendering crystal beams");
            for(BlockPos otherCrystalPos : crystalBlockEntity.getCrystalPositions()) {
                float tx = otherCrystalPos.getX() + 0.5f;
                float ty = otherCrystalPos.getY() + 0.5f;
                float tz = otherCrystalPos.getZ() + 0.5f;
                float partialTicks = 0; // partial ticks
                int totalWholeTicks = 0; // livingTicks;
                int packedLight = 0; // packed light

                float flatDistance = Mth.sqrt(tx * tx + tz * tz);
                float magnitudeSquared = tx * tx + ty * ty + tz * tz;
                float magnitude = Mth.sqrt(magnitudeSquared);

                poseStack.pushPose();
                poseStack.translate(0.0D, 2.0D, 0.0D);
                poseStack.mulPose(Vector3f.YP.rotation((float)(-Math.atan2((double)tz, (double)tx)) - 1.5707964F));
                poseStack.mulPose(Vector3f.XP.rotation((float)(-Math.atan2((double)flatDistance, (double)ty)) - 1.5707964F));
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(CRYSTAL_BORDER_LOCATION));
                // color border with getColor
                float totalTicks = 0.0F - ((float)totalWholeTicks + partialTicks) * 0.01F;
                float scroll = Mth.sqrt(magnitudeSquared) / 32.0F - ((float)totalWholeTicks + partialTicks) * 0.01F;
                float segmentX = 0.0F;
                float segmentY = 0.75F;
                float segmentZ = 0.0F;
                PoseStack.Pose pose = poseStack.last();
                Matrix4f matrix4f = pose.pose();
                Matrix3f matrix3f = pose.normal();

                for(int segment = 1; segment <= 8; ++segment) {
                    float newSegmentX = Mth.sin((float)segment * 6.2831855F / 8.0F) * 0.75F;
                    float newSegmentY = Mth.cos((float)segment * 6.2831855F / 8.0F) * 0.75F;
                    float newSegmentZ = (float)segment / 8.0F;
                    vertexConsumer.vertex(matrix4f, segmentX * 0.2F, segmentY * 0.2F, 0.0F).color(0, 0, 0, 255).uv(segmentZ, totalTicks).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
                    vertexConsumer.vertex(matrix4f, segmentX, segmentY, magnitude).color(255, 255, 255, 255).uv(segmentZ, scroll).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
                    vertexConsumer.vertex(matrix4f, newSegmentX, newSegmentY, magnitude).color(255, 255, 255, 255).uv(newSegmentZ, scroll).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
                    vertexConsumer.vertex(matrix4f, newSegmentX * 0.2F, newSegmentY * 0.2F, 0.0F).color(0, 0, 0, 255).uv(newSegmentZ, totalTicks).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
                    segmentX = newSegmentX;
                    segmentY = newSegmentY;
                    segmentZ = newSegmentZ;
                }

                poseStack.popPose();
            }
        }
    }
}