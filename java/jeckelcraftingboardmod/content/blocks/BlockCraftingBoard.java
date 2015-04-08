package jeckelcraftingboardmod.content.blocks;

import jeckelcorelibrary.api.guis.IBlockGuiActivator;
import jeckelcraftingboardmod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCraftingBoard extends Block implements IBlockGuiActivator
{
	public BlockCraftingBoard()
	{
		super(Material.wood);
		this.setLightOpacity(0);
		this.setHardness(2.5F);
		this.setStepSound(Block.soundTypeWood);
		this.setBlockName("crafting_board");
		this.setBlockTextureName("hardened_clay");
	}

	@SideOnly(Side.CLIENT)
	private IIcon blockIconTop;

	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) { return null; }

	@Override public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) { this.setBlockBounds(0.15f, 0.0f, 0.15f, 0.85f, 0.125f, 0.85f); }

	@Override public void setBlockBoundsForItemRender() { this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.4f, 1.0f); }

	@Override public boolean isOpaqueCube() { return false; }

	@Override public boolean renderAsNormalBlock() { return false; }

	@SideOnly(Side.CLIENT)
	@Override public IIcon getIcon(int side, int meta) { return (side == 1 ? this.blockIconTop : this.blockIcon); }

	@SideOnly(Side.CLIENT)
	@Override public void registerBlockIcons(IIconRegister registry)
	{
		this.blockIcon = registry.registerIcon(this.getTextureName());
		this.blockIconTop = registry.registerIcon("crafting_table_top");
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote) { return true; }
		player.openGui(Refs.getMod(), 0, world, x, y, z);
		return true;
	}


	// ##################################################
	//
	// IBlockGui Methods
	//
	// ##################################################

	@Override public Object createContainer(EntityPlayer player, World world, int x, int y, int z) { return new ContainerCrafting(player.inventory, player.worldObj, x, y, z); }

	@Override public Object createScreen(EntityPlayer player, World world, int x, int y, int z) { return new ScreenCrafting(player.inventory, player.worldObj, x, y, z); }
}
