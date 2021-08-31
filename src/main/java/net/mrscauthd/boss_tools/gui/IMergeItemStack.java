package net.mrscauthd.boss_tools.gui;

import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IMergeItemStack {
	boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection); 
}
