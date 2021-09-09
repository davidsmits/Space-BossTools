package net.mrscauthd.boss_tools.gauge;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.INBTSerializable;

public interface IGaugeFormat extends INBTSerializable<CompoundNBT> {

	boolean isShowCapacity();

	boolean isReversePercenage();

	ITextComponent getText(IGaugeValue value);

	public default double getDisplayRatio(IGaugeValue value) {
		int capacity = value.getCapacity();
		
		if (capacity == 0) {
			return 0.0D;
		}

		int stored = value.getStored();
		return (this.isReversePercenage() ? (capacity - stored) : stored) / (double) capacity;
	}

}