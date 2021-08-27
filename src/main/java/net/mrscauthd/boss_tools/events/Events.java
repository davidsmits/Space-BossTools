package net.mrscauthd.boss_tools.events;

import com.mrcrayfish.obfuscate.client.event.PlayerModelEvent;
import com.mrcrayfish.obfuscate.client.event.RenderItemEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.settings.PointOfView;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.boss_tools.ModInnet;
import net.mrscauthd.boss_tools.entity.*;
import net.mrscauthd.boss_tools.item.RoverItemItem;
import net.mrscauthd.boss_tools.item.Tier1RocketItemItem;
import net.mrscauthd.boss_tools.item.Tier2RocketItemItem;
import net.mrscauthd.boss_tools.item.Tier3RocketItemItem;


@Mod.EventBusSubscriber
public class Events {
    public static double counter = 1;
    public static boolean check = false;
    //Player Tick
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Entity entity = event.player;
            World world = entity.world;
            double x = entity.getPosX();
            double y = entity.getPosY();
            double z = entity.getPosZ();
            //Venus Rain
            {
                if (((world instanceof World ? (((World) world).getDimensionKey()) : World.OVERWORLD) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus"))))) {
                    if (((PlayerEntity) entity).abilities.isCreativeMode == false && entity.isSpectator() == false) {
                        if (((world.getWorldInfo().isRaining() == (true)) && ((world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (int) (Math.floor(x)), (int) (Math.floor(z)))) <= ((Math.floor(y)) + 1)))) {
                            ((LivingEntity) entity).attackEntityFrom(new DamageSource("venus.acid").setDamageBypassesArmor(), (float) 1);
                        }
                    }
                }
            }
            //Player orbit Fall Teleport
            {
                if (entity.getPosY() <= 1 && !(entity.getRidingEntity() instanceof LandingGearEntity.CustomEntity)) {
                    RegistryKey<World> world2 = (world instanceof World ? (((World) world).getDimensionKey()) : World.OVERWORLD);
                    //Overworld
                    if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:overworld_orbit"))) {
                        {
                            Entity _ent = entity;
                            if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
                                RegistryKey<World> destinationType = World.OVERWORLD;
                                ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
                                if (nextWorld != null) {
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
                                    ((ServerPlayerEntity) _ent).teleport(nextWorld, entity.getPosX(), 450, entity.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
                                    for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
                                        ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
                                    }
                                }
                            }
                        }
                    }
                    //Moon
                    if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon_orbit"))) {
                        {
                            Entity _ent = entity;
                            if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
                                RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon"));
                                ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
                                if (nextWorld != null) {
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
                                    ((ServerPlayerEntity) _ent).teleport(nextWorld, entity.getPosX(), 450, entity.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
                                    for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
                                        ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
                                    }
                                }
                            }
                        }
                    }
                    //Mars
                    if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars_orbit"))) {
                        {
                            Entity _ent = entity;
                            if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
                                RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars"));
                                ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
                                if (nextWorld != null) {
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
                                    ((ServerPlayerEntity) _ent).teleport(nextWorld, entity.getPosX(), 450, entity.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
                                    for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
                                        ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
                                    }
                                }
                            }
                        }
                    }
                    //Mercury
                    if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury_orbit"))) {
                        {
                            Entity _ent = entity;
                            if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
                                RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury"));
                                ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
                                if (nextWorld != null) {
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
                                    ((ServerPlayerEntity) _ent).teleport(nextWorld, entity.getPosX(), 450, entity.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
                                    for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
                                        ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
                                    }
                                }
                            }
                        }
                    }
                    //Venus
                    if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus_orbit"))) {
                        {
                            Entity _ent = entity;
                            if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
                                RegistryKey<World> destinationType = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus"));
                                ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
                                if (nextWorld != null) {
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(SChangeGameStatePacket.field_241768_e_, 0));
                                    ((ServerPlayerEntity) _ent).teleport(nextWorld, entity.getPosX(), 450, entity.getPosZ(), _ent.rotationYaw, _ent.rotationPitch);
                                    ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
                                    for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
                                        ((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //Lander Warning Overlay Tick
                if (check == false) {
                    counter = counter - 0.025;
                    if (counter <= 0.2) {
                        check = true;
                    }
                }
                if (check == true) {
                    counter = counter + 0.025;
                    if (counter >= 1.2) {
                        check = false;
                    }
                }
            //Other Player Ticks
        }
    }

    //OnEntityTick Event
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
        Entity entity = event.getEntityLiving();
        World world = entity.world;
        double x = entity.getPosX();
        double y = entity.getPosY();
        double z = entity.getPosZ();
        if (Config.EntityOxygenSystem == true) {
            if ((EntityTypeTags.getCollection().getTagByID(new ResourceLocation(("forge:entities/space").toLowerCase(java.util.Locale.ENGLISH))).contains(entity.getType()))) {
                if ((((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:overworld_orbit")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon_orbit")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars_orbit")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury_orbit")))) || (((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus")))) || ((entity.world.getDimensionKey()) == (RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus_orbit"))))))))))))) {
                    entity.getPersistentData().putDouble("tick", ((entity.getPersistentData().getDouble("tick")) + 1));
                    if (((entity.getPersistentData().getDouble("tick")) >= 15)) {
                        world.addParticle(ParticleTypes.SMOKE, x, (y + 1), z, 0, 0, 0);
                        if (entity instanceof LivingEntity) {
                            ((LivingEntity) entity).attackEntityFrom(new DamageSource("space").setDamageBypassesArmor(), (float) 1);
                        }
                        entity.getPersistentData().putDouble("tick", 0);
                    }
                }
            }
        }
    }

    //Camera Event
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void CameraPos(EntityViewRenderEvent.CameraSetup event) {
        if (event.getInfo().getRenderViewEntity().getRidingEntity() instanceof RocketTier1Entity.CustomEntity || event.getInfo().getRenderViewEntity().getRidingEntity() instanceof RocketTier2Entity.CustomEntity || event.getInfo().getRenderViewEntity().getRidingEntity() instanceof RocketTier3Entity.CustomEntity || event.getInfo().getRenderViewEntity().getRidingEntity() instanceof LandingGearEntity.CustomEntity) {
            if (Minecraft.getInstance().gameSettings.getPointOfView().equals(PointOfView.THIRD_PERSON_FRONT)) {
                event.getInfo().movePosition(-event.getInfo().calcCameraDistance(8d), 0d, 0);
            }
            if (Minecraft.getInstance().gameSettings.getPointOfView().equals(PointOfView.THIRD_PERSON_BACK)) {
                event.getInfo().movePosition(-event.getInfo().calcCameraDistance(8d), 0d, 0);
            }
        }
    }

    //Render Player Event
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void render(RenderPlayerEvent event) {
        if (((event.getEntity().getRidingEntity()) instanceof LandingGearEntity.CustomEntity)) {
            event.getMatrixStack().scale(0f, 0f, 0f);
            // event.getRenderer();
        }
    }

    //Obfuscate Rotation Event
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void setupPlayerAngles(PlayerModelEvent.SetupAngles.Post event) {
        PlayerEntity player = event.getPlayer();
        PlayerModel model = event.getModelPlayer();
        //Player Rocket Sit Rotations
        {
            if (player.getRidingEntity() instanceof RocketTier1Entity.CustomEntity || player.getRidingEntity() instanceof RocketTier2Entity.CustomEntity || player.getRidingEntity() instanceof RocketTier3Entity.CustomEntity) {
                model.bipedRightLeg.rotationPointY = (float) Math.toRadians(485F);
                model.bipedLeftLeg.rotationPointY = (float) Math.toRadians(485F);
                model.bipedRightLeg.rotateAngleX = (float) Math.toRadians(0F);
                model.bipedLeftLeg.rotateAngleX = (float) Math.toRadians(0F);
                model.bipedLeftLeg.rotateAngleY = (float) Math.toRadians(3F);
                model.bipedRightLeg.rotateAngleY = (float) Math.toRadians(3F);
                // Arms
                model.bipedRightArm.rotationPointX = (float) Math.toRadians(-250F);// -200
                model.bipedLeftArm.rotationPointX = (float) Math.toRadians(250F);
                model.bipedLeftArm.rotateAngleX = (float) -0.07;
                model.bipedRightArm.rotateAngleX = (float) -0.07;
            }
        }
        //Player Hold Vehicles Rotation
        {
            if (!(player.getRidingEntity() instanceof RocketTier1Entity.CustomEntity) && !(player.getRidingEntity() instanceof RocketTier2Entity.CustomEntity) && !(player.getRidingEntity() instanceof RocketTier3Entity.CustomEntity)) {
                Item item1 = ((player instanceof LivingEntity) ? ((LivingEntity) player).getHeldItemMainhand() : ItemStack.EMPTY).getItem();
                Item item2 = ((player instanceof LivingEntity) ? ((LivingEntity) player).getHeldItemOffhand() : ItemStack.EMPTY).getItem();
                if (item1 == new ItemStack(Tier1RocketItemItem.block, (int) (1)).getItem()
                        || item1 == new ItemStack(Tier2RocketItemItem.block, (int) (1)).getItem()
                        || item1 == new ItemStack(Tier3RocketItemItem.block, (int) (1)).getItem()
                        || item1 == new ItemStack(RoverItemItem.block, (int) (1)).getItem()
                        //Off Hand
                        || item2 == new ItemStack(Tier1RocketItemItem.block, (int) (1)).getItem()
                        || item2 == new ItemStack(Tier2RocketItemItem.block, (int) (1)).getItem()
                        || item2 == new ItemStack(Tier3RocketItemItem.block, (int) (1)).getItem()
                        || item2 == new ItemStack(RoverItemItem.block, (int) (1)).getItem()) {
                    model.bipedRightArm.rotateAngleX = (float) 10;
                    model.bipedLeftArm.rotateAngleX = (float) 10;
                    model.bipedLeftArm.rotateAngleZ = (float) 0;
                    model.bipedRightArm.rotateAngleZ = (float) 0;
                    model.bipedRightArm.rotateAngleY = (float) 0;
                    model.bipedLeftArm.rotateAngleY = (float) 0;
                }
            }
        }
    }

    //Obfuscate Item Render Event
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void ItemRender(RenderItemEvent.Held event) {
        Entity player = event.getEntity();
        if (player.getRidingEntity() instanceof RocketTier1Entity.CustomEntity || player.getRidingEntity() instanceof RocketTier2Entity.CustomEntity || player.getRidingEntity() instanceof RocketTier3Entity.CustomEntity) {
            event.setCanceled(true);
        }
    }

    //TODO:Change Dimension Id in Teleport Code
    //World Tick Event
    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            World world = event.world;
            RegistryKey<World> world2 = (world instanceof World ? (((World) world).getDimensionKey()) : World.OVERWORLD);
            if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon_orbit"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars_orbit"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury_orbit"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus_orbit"))
             || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:overworld_orbit"))) {
                world.thunderingStrength = 0;
                world.rainingStrength = 0;
            }
            if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus"))) {
                world.thunderingStrength = 0;
            }
        }
    }

    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() instanceof PlayerEntity) {
            PlayerEntity entity = (PlayerEntity) event.getEntity();

            Boolean armorCheck1 = Events.Nethrite_Space_Suit_Check(entity);

            if (armorCheck1 == true) {
                if (event.getSource().isFireDamage()) {
                    entity.forceFireTicks(0);
                    event.setCanceled(true);
                }
            }
        }
    }
    //Other Events

    //Methodes
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

    public static boolean Dimcheck(World world) {
        RegistryKey<World> world2 = (world instanceof World ? (((World) world).getDimensionKey()) : World.OVERWORLD);
        if (world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:moon_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mars_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:mercury_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus_orbit"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:venus"))
                || world2 == RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation("boss_tools:overworld_orbit"))) {
            return true;
        }
        return false;
    }
}
