package net.mrscauthd.boss_tools.procedures;

import net.mrscauthd.boss_tools.BossToolsModVariables;
import net.mrscauthd.boss_tools.BossToolsMod;

import net.minecraft.entity.Entity;

import java.util.Map;

public class AlienOnEntityTickUpdateProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				BossToolsMod.LOGGER.warn("Failed to load dependency entity for procedure AlienOnEntityTickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		// Config
		if (((BossToolsModVariables.Config) == 2)) {
			if (!entity.world.isRemote())
				entity.remove();
		}
	}
}
