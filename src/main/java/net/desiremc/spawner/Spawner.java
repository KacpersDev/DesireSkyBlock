package net.desiremc.spawner;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class Spawner {

    public Spawner(Player player, EntityType type, int amount){

        ItemStack testSpawner = new ItemStack(Material.MOB_SPAWNER, 1);
        BlockStateMeta bsm = (BlockStateMeta) testSpawner.getItemMeta();
        CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();

        cs.setSpawnedType(type);
        bsm.setBlockState(cs);
        testSpawner.setItemMeta(bsm);

        testSpawner.setAmount(amount);

        player.getInventory().addItem(testSpawner);
    }
}
