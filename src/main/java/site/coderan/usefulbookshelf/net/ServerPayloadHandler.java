package site.coderan.usefulbookshelf.net;

import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import site.coderan.usefulbookshelf.ModConfigs;

import java.util.List;

public class ServerPayloadHandler {
    public static void handleDataOnNetwork(final ConfigChangeData data, final IPayloadContext context){
        // 原封不动把消息给Client(所有服务器玩家的Client)，让所有服务器玩家同步修改Common Config
        PacketDistributor.sendToAllPlayers(new ConfigChangeData(data.mode(), data.descriptionId()));
        if (data.mode() == 0) {
            // 将descriptionId维护进Common Config中
            List<? extends String> newConfig = ModConfigs.ACCEPT_STORED_ITEMS.get();
            if (!newConfig.contains(data.descriptionId())){
                ((List<String>)newConfig).add(data.descriptionId());
                ModConfigs.ACCEPT_STORED_ITEMS.set(newConfig);
                ModConfigs.ACCEPT_STORED_ITEMS.save();
            }
        }
        if (data.mode() == 1){
            // 将descriptionId从Common Config中去除
            List<? extends String> newConfig = ModConfigs.ACCEPT_STORED_ITEMS.get();
            if (newConfig.contains(data.descriptionId())){
                ((List<String>)newConfig).remove(data.descriptionId());
                ModConfigs.ACCEPT_STORED_ITEMS.set(newConfig);
                ModConfigs.ACCEPT_STORED_ITEMS.save();
            }
        }
    }
}
