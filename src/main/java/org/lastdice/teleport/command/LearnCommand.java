package org.lastdice.teleport.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LearnCommand extends BukkitCommand {

    public LearnCommand(@NotNull String name) {
        super(name);
    }




    @Override
    public boolean execute(@NotNull CommandSender Sender, @NotNull String commandLabel, @NotNull String[] args) {
        //Bukkit.getLogger().info("테스트 커맨드 실행");
        if (Sender instanceof Player player) {
            //Bukkit.getLogger().warning(player.getName());
            Random random = new Random();
            List<Integer> List = new ArrayList<>();
            // X,Z 에서의 Y 좌표 중 안전구역이 여러 개일 경우 그 값들을 저장하는 공간
            World world = player.getWorld();
            //맵은 지금 플레이어가 있는 맵으로 지정
            Location playerLocation = player.getLocation();
            //플레이어의 위치 정보를 얻는 줄

            int randomX = (int)(Math.random() * 20001 ) - 10000;
            int randomZ = (int)(Math.random() * 20001 ) - 10000;
            // 0에서 부터 20000의 좌표에서 - 10000을 하여서 좌표 생성 19000 - 10000 = 9000좌표

            int Y = world.getHighestBlockYAt(randomX, randomZ );
            // getHighestBlockYAt로 X,Z에서 고른 좌표 중 Y에서 가장 높은 블럭의 좌표로 설정
            //Block block = world.getBlockAt(randomX, Y - 1, playerLocation.getBlockZ());
            //Material type = block.getType();

            for ( int i = Y ; i > -64 ; i--) {
                // 설정한 Y 값에서 -64 가장 아래 위치의 블럭까지 -1로 좌표를 수정
                Location location = new Location(world, randomX, i, randomZ);
                if(isSafeZone(location)) {
                    //만약 그 좌표가 아래 isSafeZone 의 조건에 맞다면 아래 List 에 추가
                    List.add(location.getBlockY());
                    //int randomY = (int) (Math.random() * List.size());
                }
            }
//            int num = (int) (Math.random() * List.size());
//
//           Location teleport = List.;

            Location safeLocation = new Location(world,randomX,List.get(random.nextInt(List.size())) + 1,randomZ);
            //safeLocation 에 위에서 저한 랜덤X, 랜덤 Z list 에 있는 안전한 y 좌표를 가져와서 설정
//            Location location = new Location(world, randomX, randomY , randomZ);
            player.teleport(safeLocation);
            //player.teleport 에서 위에 safeLocation 의 값을 가져와서 이동
        }

        return false;
    }
    public boolean isSafeZone(Location yZon) {
        //isSafeZone 에서 boolean 으로 참 거짓을 구별하는 메소드를 생성
        return yZon.getBlock().getRelative(0,1,0).getType().isAir()
                && yZon.getBlock().getRelative(0,2,0).getType().isAir()
                && !yZon.getBlock().isPassable();
        // 안전구역 조건으로 첫 줄: Location 에서의 블럭의 1칸 위가 공기이다
        // 둘 째 줄: Location 에서의 2칸 위에 블럭이 공기이다
        // 셋 째 줄: Location 의 블럭이 통과 가능한 블럭(꽃, 잔디, 물, 용암) 이 없어야한다
    }
}
