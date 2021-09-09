package net.mrscauthd.boss_tools.capability;

import mekanism.api.Action;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.gas.IGasHandler;
import mekanism.common.registries.MekanismGases;

public class OxygenStorageGasAdapter implements IGasHandler {

	private IOxygenStorage oxygenStorage;
	private boolean canExtract;
	private boolean canInsert;

	public OxygenStorageGasAdapter(IOxygenStorage oxygenStorage) {
		this(oxygenStorage, true, true);
	}

	public OxygenStorageGasAdapter(IOxygenStorage oxygenStorage, boolean canExtract, boolean canInsert) {
		this.oxygenStorage = oxygenStorage;
		this.canExtract = canExtract;
		this.canInsert = canInsert;
	}

	public Gas getGas() {
		return MekanismGases.OXYGEN.get();
	}

	protected GasStack createGasStack(int amount) {
		return new GasStack(this.getGas(), amount);
	}

	@Override
	public GasStack extractChemical(int var1, long var2, Action var4) {
		if (this.isCanExtract()) {
			return this.createGasStack(this.getOxygenStorage().extractOxygen((int) var2, var4.simulate()));
		} else {
			return GasStack.EMPTY;
		}
	}

	@Override
	public GasStack getChemicalInTank(int var1) {
		return this.createGasStack(this.getOxygenStorage().getOxygenStored());
	}

	@Override
	public long getTankCapacity(int var1) {
		return this.getOxygenStorage().getMaxOxygenStored();
	}

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public GasStack insertChemical(int var1, GasStack var2, Action var3) {
		if (this.isCanInsert() && this.isValid(var1, var2)) {
			var2 = var2.copy();
			var2.shrink(this.getOxygenStorage().receiveOxygen((int) var2.getAmount(), var3.simulate()));
			return var2;
		} else {
			return var2;
		}
	}

	@Override
	public void setChemicalInTank(int var1, GasStack var2) {
		if (this.isCanInsert() && this.isValid(var1, var2)) {
			this.getOxygenStorage().setOxygenStored((int) var2.getAmount());
		}
	}

	@Override
	public boolean isValid(int var1, GasStack var2) {
		return var2.getType() == this.getGas();
	}

	public IOxygenStorage getOxygenStorage() {
		return this.oxygenStorage;
	}

	public boolean isCanExtract() {
		return this.canExtract;
	}

	public boolean isCanInsert() {
		return this.canInsert;
	}

}
