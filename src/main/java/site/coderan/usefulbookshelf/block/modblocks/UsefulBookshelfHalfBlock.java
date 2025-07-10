package site.coderan.usefulbookshelf.block.modblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import site.coderan.usefulbookshelf.ModMain;
import site.coderan.usefulbookshelf.block.modblockentities.UsefulBookshelfHalfBlockEntity;
import site.coderan.usefulbookshelf.gui.modmenus.UsefulBookshelfMenu;
import site.coderan.usefulbookshelf.sounds.ModSounds;

import java.util.ArrayList;
import java.util.List;

public class UsefulBookshelfHalfBlock  extends Block implements EntityBlock, SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED  = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    VoxelShape boxFacingSouth = Block.box(0, 0, 0, 16, 16, 9);
    VoxelShape boxFacingNorth = Block.box(0, 0, 7, 16, 16, 16);
    VoxelShape boxFacingEast = Block.box(0, 0, 0, 9, 16, 16);
    VoxelShape boxFacingWest = Block.box(7, 0, 0, 16, 16, 16);
    public UsefulBookshelfHalfBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }
    @Override
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.NORMAL;
    }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        if (direction == Direction.NORTH) {
            return boxFacingNorth;
        } else if (direction == Direction.EAST) {
            return boxFacingEast;
        } else if (direction == Direction.SOUTH) {
            return boxFacingSouth;
        }else {
            return boxFacingWest;
        }
    }

    // isFlammable、getFlammability和getFireSpreadSpeed配合properties中的ignitedByLava()方法
    // 定义书架是否可以被燃烧、燃烧的速度、火势传播速度
    // ignitedByLava()方法允许书架会被岩浆烧着
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    protected FluidState getFluidState(BlockState p_56969_) {
        return (Boolean)p_56969_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_56969_);
    }

    // 玩家放置方块时，设置block的property
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext actionPlayer) {
        Direction facing = actionPlayer.getHorizontalDirection().getOpposite();
        BlockPos blockpos = actionPlayer.getClickedPos();
        FluidState fluidstate = actionPlayer.getLevel().getFluidState(blockpos);
        return this.defaultBlockState().setValue(FACING, facing).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    // 为方块添加property
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, WATERLOGGED});
    }

    // getDrops和onRemove配合来定义书架的掉落物
    // 正常收集掉落的物品，正常收集指的是玩家慢慢打破（被烧掉等是非正常的）
    // getDrops无法动态设置收集物所以配合onRemove使用
    @Override
    protected List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        List<ItemStack> drops = new ArrayList<>();
        // drops.add(new ItemStack(ModItems.USEFUL_BOOKSHELF_HALF));
        return drops;
    }
    // 掉落出书架上储存的物品，就算是非正常收集也会掉落
    @Override
    protected void onRemove(BlockState blockStateOld, Level level, BlockPos blockPos, BlockState blockStateNew, boolean p_60519_) {
        if (blockStateNew.getBlock() == Blocks.AIR || blockStateNew.getBlock() == Blocks.WATER || blockStateNew.getBlock() == Blocks.LAVA || blockStateNew.getBlock() == Blocks.FIRE || blockStateNew.getBlock() == Blocks.SOUL_FIRE){
            UsefulBookshelfHalfBlockEntity blockEntity = (UsefulBookshelfHalfBlockEntity) level.getBlockEntity(blockPos);
            ItemStackHandler bookshelfInventory = blockEntity.getBookshelfInventory();
            for (int i = 0; i < bookshelfInventory.getSlots(); i++) {
                ItemStack stackInSlot = bookshelfInventory.getStackInSlot(i);
                if (!stackInSlot.isEmpty()) {
                    bookshelfInventory.setStackInSlot(i, ItemStack.EMPTY);
                    level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stackInSlot));
                }
            }
            super.onRemove(blockStateOld, level, blockPos, blockStateNew, p_60519_);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new UsefulBookshelfHalfBlockEntity(blockPos, blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        UsefulBookshelfHalfBlockEntity blockEntity = (UsefulBookshelfHalfBlockEntity) level.getBlockEntity(blockPos);
        Direction hitDirection = blockHitResult.getDirection();
        MenuProvider menuProvider = this.getMenuProvider(blockState, level, blockPos, hitDirection);
        if (!level.isClientSide() && blockEntity instanceof UsefulBookshelfHalfBlockEntity && player instanceof ServerPlayer) {
            if (menuProvider != null){
                player.openMenu(menuProvider);
                // play sound when open the bookshelf
                level.playSound(player, blockPos, ModSounds.BOOKSHELF_SOUND.value(), SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            } else {
                if (player.getMainHandItem().getItem() == Items.WATER_BUCKET) {
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.PASS;
            }
        } else {
            if (menuProvider != null) {
                return InteractionResult.SUCCESS;
            } else {
                if (player.getMainHandItem().getItem() == Items.WATER_BUCKET) {
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.PASS;
            }
        }
    }

    protected MenuProvider getMenuProvider(BlockState blockState, Level level, BlockPos blockPos, Direction hitDirection) {
        UsefulBookshelfHalfBlockEntity blockEntity = (UsefulBookshelfHalfBlockEntity) level.getBlockEntity(blockPos);
        String blockFacing = blockState.getValue(FACING).getName();
        ItemStackHandler bookshelfInventory = blockEntity.getBookshelfInventory(hitDirection, blockFacing);
        if (bookshelfInventory != null){
            return new SimpleMenuProvider(
                    (containerId, playerInventory, player) -> new UsefulBookshelfMenu(containerId, playerInventory, blockEntity.getBookshelfInventory(hitDirection, blockFacing)),
                    Component.translatable(ModMain.MOD_ID + ".menu.useful_bookshelf_inventory")
            );
        } else {
            return null;
        }
    }
}
