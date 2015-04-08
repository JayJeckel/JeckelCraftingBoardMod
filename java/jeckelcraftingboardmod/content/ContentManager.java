package jeckelcraftingboardmod.content;

import jeckelcorelibrary.api.managers.IContentManager;
import jeckelcorelibrary.core.blocks.BlockMetaColored;
import jeckelcorelibrary.core.blocks.BlockMetaWooden;
import jeckelcorelibrary.core.blocks.ItemBlockMetaColored;
import jeckelcorelibrary.core.blocks.ItemBlockMetaWooden;
import jeckelcorelibrary.core.tabs.MappedCreativeTab;
import jeckelcorelibrary.utils.GameRegUtil;
import jeckelcraftingboardmod.content.blocks.BlockColoredCraftingBoard;
import jeckelcraftingboardmod.content.blocks.BlockCraftingBoard;
import jeckelcraftingboardmod.content.blocks.BlockWoodenCraftingBoard;
import jeckelcraftingboardmod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class ContentManager implements IContentManager
{
	public static class ModBlocks
	{
		public static Block crafting_board;

		public static Block crafting_board_wooden;

		public static Block crafting_board_colored;
	}

	public static class ModItems
	{

	}

	@Override public void pre()
	{
		ModBlocks.crafting_board = new BlockCraftingBoard();
		GameRegUtil.block(ModBlocks.crafting_board);

		ModBlocks.crafting_board_wooden = new BlockWoodenCraftingBoard();
		GameRegUtil.block(ModBlocks.crafting_board_wooden, ItemBlockMetaWooden.class, null);

		ModBlocks.crafting_board_colored = new BlockColoredCraftingBoard();
		GameRegUtil.block(ModBlocks.crafting_board_colored, ItemBlockMetaColored.class, null);


		MappedCreativeTab tab = new MappedCreativeTab(Refs.ModId);
		tab.addBlock(Refs.ModId, ModBlocks.crafting_board);
		tab.addBlock(Refs.ModId, ModBlocks.crafting_board_wooden);
		tab.addBlock(Refs.ModId, ModBlocks.crafting_board_colored);
		tab.setIconItemStack(new ItemStack(ModBlocks.crafting_board));
	}

	@Override public void initialize()
	{
		GameRegUtil.recipeShaped(new ItemStack(ModBlocks.crafting_board, 1),
				"??",
				"##",
				'?', "plankWood",
				'#', new ItemStack(Blocks.hardened_clay));

		for (int meta = 0; meta < BlockMetaWooden.woods.length; meta++)
		{
			GameRegUtil.recipeShaped(new ItemStack(ModBlocks.crafting_board_wooden, 1, meta),
					"#?",
					'?', BlockMetaWooden.getPlankStack(meta),
					'#', new ItemStack(ModBlocks.crafting_board));
		}

		for (int meta = 0; meta < BlockMetaColored.colors.length; meta++)
		{
			GameRegUtil.recipeShaped(new ItemStack(ModBlocks.crafting_board_colored, 1, meta),
					"#?",
					'?', BlockMetaColored.getDyeStack(meta),
					'#', new ItemStack(ModBlocks.crafting_board));
		}
	}

	@Override public void post()
	{
	}
}
