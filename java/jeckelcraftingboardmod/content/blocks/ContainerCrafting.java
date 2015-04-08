package jeckelcraftingboardmod.content.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class ContainerCrafting extends ContainerWorkbench
{
	public ContainerCrafting(InventoryPlayer inventory, World world, int x, int y, int z)
	{
		super(inventory, world, x, y, z);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	private int posX;
	private int posY;
	private int posZ;

	@Override public boolean canInteractWith(EntityPlayer player)
	{
		return player.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
	}
}
