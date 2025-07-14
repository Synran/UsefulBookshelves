package site.coderan.usefulbookshelf.block.modblocks;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import site.coderan.usefulbookshelf.ModConfigs;
import site.coderan.usefulbookshelf.ModMain;

import java.util.List;

@EventBusSubscriber(modid = ModMain.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerRightClickEvent {
    @SubscribeEvent
    public static void withCrouching(PlayerInteractEvent.RightClickBlock event){
        if (!event.getLevel().isClientSide && event.getHand() == InteractionHand.MAIN_HAND){
            if (event.getEntity().isCrouching() && event.getEntity().getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.PAPER){
                // 拿到被右击的方块
                BlockPos pos = event.getPos();
                BlockState blockState = Minecraft.getInstance().level.getBlockState(pos);
                Block block = blockState.getBlock();
                ModBlocks.holders.forEach((holder, s) -> {
                    if (holder.get() == block){
                        // 获取玩家手中的itemstack
                        ItemStack itemStack = event.getItemStack();
                        String descriptionId = itemStack.getItem().getDescriptionId();
                        // 将descriptionId维护进Common Config中
                        List<? extends String> newConfig = ModConfigs.ACCEPT_STORED_ITEMS.get();
                        if (!newConfig.contains(descriptionId)){
                            ((List<String>)newConfig).add(descriptionId);
                            ModConfigs.ACCEPT_STORED_ITEMS.set(newConfig);
                            ModConfigs.ACCEPT_STORED_ITEMS.save();
                            // 打印消息通知玩家配置成功
                            event.getEntity().sendSystemMessage(Component.translatable("chat.config.usefulbookshelf.success"));
                        }
                    }
                });
                ModBlocks.holdersHalf.forEach((holder, s) -> {
                    if (holder.get() == block){
                        // 获取玩家手中的itemstack
                        ItemStack itemStack = event.getItemStack();
                        String descriptionId = itemStack.getItem().getDescriptionId();
                        // 将descriptionId维护进Common Config中
                        List<? extends String> newConfig = ModConfigs.ACCEPT_STORED_ITEMS.get();
                        if (!newConfig.contains(descriptionId)){
                            ((List<String>)newConfig).add(descriptionId);
                            ModConfigs.ACCEPT_STORED_ITEMS.set(newConfig);
                            ModConfigs.ACCEPT_STORED_ITEMS.save();
                            // 打印消息通知玩家配置成功
                            event.getEntity().sendSystemMessage(Component.translatable("chat.config.usefulbookshelf.success"));
                        }
                    }
                });
            }
        }
    }
}
