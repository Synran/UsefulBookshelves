package site.coderan.usefulbookshelf.net;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import site.coderan.usefulbookshelf.ModConfigs;

import java.util.List;

public class ClientPayloadHandler {
    public static void handleDataOnNetwork(final ConfigChangeData data, final IPayloadContext context){
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
