package com.locipro.neoballerite.item.custom;

import com.locipro.neoballerite.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class BalleritePickaxeItem extends PickaxeItem {
    public BalleritePickaxeItem(Tier p_42961_, Properties p_42964_) {
        super(p_42961_, p_42964_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        Vec3 vectorToPlayer = miningEntity.getViewVector(3f);
        System.out.println("Eugh");


//        Random random = new Random();
//        for (int i = 0; i <=10; i++) {
//            float delta1 = random.nextFloat(0f, 0.25f);
//            float delta2 = random.nextFloat(0f, 0.25f);
//            float delta3 = random.nextFloat(0f, 0.25f);
//            //System.out.println("post:" + new Vec3(pos.getX() - 0.25f + delta1, pos.getY()- 0.25f + delta2, pos.getZ()- 0.25f + delta3));
//            level.addParticle(ParticleTypes.ASH,
//                    pos.getX() - 0.25f + delta1,
//                    pos.getY()- 0.25f + delta2,
//                    pos.getZ()- 0.25f + delta3,
//                    vectorToPlayer.x*0.25, vectorToPlayer.y*0.25, vectorToPlayer.z*0.25);
//        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }
}
