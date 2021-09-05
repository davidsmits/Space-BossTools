package net.mrscauthd.boss_tools.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SStopSoundPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrscauthd.boss_tools.ModInnet;
import net.mrscauthd.boss_tools.entity.*;
import net.mrscauthd.boss_tools.entity.pygro.PygroEntity;
import net.mrscauthd.boss_tools.item.RoverItemItem;
import net.mrscauthd.boss_tools.item.Tier1RocketItemItem;
import net.mrscauthd.boss_tools.item.Tier2RocketItemItem;
import net.mrscauthd.boss_tools.item.Tier3RocketItemItem;
import slimeknights.mantle.inventory.ItemHandlerSlot;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.ArrayList;
import java.util.List;

public class Methodes {
    public static void PlayerFallToPlanet(PlayerEntity entity, ResourceLocation Planet) {
        if (entity.getPosY() <= 1 && !(entity.getRidingEntity() instanceof LanderEntity.CustomEntity) && !entity.world.isRemote) {

            RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, Planet);
            ServerWorld nextWorld = entity.getServer().getWorld(destinationType);

            if (nextWorld != null) {
                ((ServerPlayerEntity) entity).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
                ((ServerPlayerEntity) entity).teleport(nextWorld, entity.getPosX(), 450, entity.getPosZ(), entity.rotationYaw, entity.rotationPitch);
                ((ServerPlayerEntity) entity).connection.sendPacket(new SPlayerAbilitiesPacket(entity.abilities));

                for (EffectInstance effectinstance : entity.getActivePotionEffects()) {
                    ((ServerPlayerEntity) entity).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
                }
            }
        }
    }

    public static boolean Nethrite_Space_Suit_Check(PlayerEntity player) {
        Boolean item3 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 3)).getItem() == ModInnet.NETHERITE_OXYGEN_MASK.get();
        Boolean item2 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 2)).getItem() == ModInnet.NETHERITE_SPACE_SUIT.get();
        Boolean item1 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 1)).getItem() == ModInnet.NETHERITE_SPACE_PANTS.get();
        Boolean item0 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 0)).getItem() == ModInnet.NETHERITE_SPACE_BOOTS.get();

        if (item0 && item1 && item2 && item3) {
            return true;
        }
        return false;
    }

    public static boolean Space_Suit_Check(PlayerEntity player) {
        Boolean item3 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 3)).getItem() == ModInnet.OXYGEN_MASK.get();
        Boolean item2 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 2)).getItem() == ModInnet.SPACE_SUIT.get();
        Boolean item1 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 1)).getItem() == ModInnet.SPACE_PANTS.get();
        Boolean item0 = player.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, 0)).getItem() == ModInnet.SPACE_BOOTS.get();

        if (item0 && item1 && item2 && item3) {
            return true;
        }
        return false;
    }

    public static boolean DimCheck(World world) {
        RegistryKey<World> world2 = world.getDimensionKey();
        if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:overworld_orbit"))) {
            return true;
        }
        return false;
    }

    public static boolean RocketCheckOr(Entity entity) {
        if (entity instanceof RocketTier1Entity || entity instanceof RocketTier2Entity || entity instanceof RocketTier3Entity) {
            return true;
        }
        return false;
    }

    public static boolean AllVehiclesOr(Entity entity) {
        if (entity instanceof RocketTier1Entity || entity instanceof RocketTier2Entity || entity instanceof RocketTier3Entity || entity instanceof LanderEntity.CustomEntity || entity instanceof RoverEntity.CustomEntity) {
            return true;
        }
        return false;
    }

    public static void RocketSounds(World world, BlockPos pos) {
        if (!world.isRemote()) {
            world.playSound(null, pos, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("boss_tools:rocketfly")), SoundCategory.NEUTRAL,3,1);
        } else {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("boss_tools:rocketfly")), SoundCategory.NEUTRAL, 3, 1, false);
        }
    }

    public static void StopRocketSounds(ServerPlayerEntity sourceentity) {
        SoundCategory soundCategory = SoundCategory.NEUTRAL;
        SStopSoundPacket sstopsoundpacket = new SStopSoundPacket(new ResourceLocation("boss_tools:rocketfly"), soundCategory);

        if (sourceentity instanceof ServerPlayerEntity) {
            sourceentity.connection.sendPacket(sstopsoundpacket);
        }
    }

    public static void DropRocket(PlayerEntity player) {
        Item item1 = player.getHeldItemMainhand().getItem();
        Item item2 = player.getHeldItemOffhand().getItem();

        List<Item> items = new ArrayList<Item>();

        items.add(Tier1RocketItemItem.block);
        items.add(Tier2RocketItemItem.block);
        items.add(Tier3RocketItemItem.block);
        items.add(RoverItemItem.block);

        if (items.contains(item1) && items.contains(item2)) {

            ItemEntity spawn = new ItemEntity(player.world, player.getPosX(),player.getPosY(),player.getPosZ(), new ItemStack(item2));
            spawn.setPickupDelay(0);
            player.world.addEntity(spawn);

            player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(capability -> {
                capability.extractItem(40, 1, false); //40 is offhand
            });

        }

    }

    /**If a entity should not get Damage add it to the Tag "venus_rain", and if you has a Entity like a car return the damage to false*/
    public static void VenusRain(LivingEntity entity, ResourceLocation planet) {
        if (entity.world.getDimensionKey() == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, planet)) {
            if (entity.world.getWorldInfo().isRaining() == (true) && entity.world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int) Math.floor(entity.getPosX()), (int) Math.floor(entity.getPosZ())) <= Math.floor(entity.getPosY()) + 1) {
                if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation(("forge:entities/venus_rain").toLowerCase(java.util.Locale.ENGLISH))).contains(entity.getType()) == false) {

                    entity.attackEntityFrom(new DamageSource("venus.acid").setDamageBypassesArmor(), 1);
                }
            }
        }

    }

    public static void EntityOxygen(LivingEntity entity, World world) {
        if (Config.EntityOxygenSystem == true) {
            if (EntityTypeTags.getCollection().getTagByID(new ResourceLocation(("forge:entities/oxygen").toLowerCase(java.util.Locale.ENGLISH))).contains(entity.getType())) {
                if (Methodes.DimCheck(world)) {
                    entity.getPersistentData().putDouble("tick", entity.getPersistentData().getDouble("tick") + 1);

                    if (entity.getPersistentData().getDouble("tick") > 15) {

                        world.addParticle(ParticleTypes.SMOKE, entity.getPosX(), entity.getPosY() + 1, entity.getPosZ(), 0, 0, 0);
                        entity.attackEntityFrom(new DamageSource("oxygen").setDamageBypassesArmor(),1);

                        entity.getPersistentData().putDouble("tick", 0);
                    }
                }
            }
        }

    }

}
