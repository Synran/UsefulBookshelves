package site.coderan.usefulbookshelf.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.coderan.usefulbookshelf.ModMain;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_REGISTER = DeferredRegister.create(
            Registries.SOUND_EVENT,
            ModMain.MOD_ID
    );
    public static final Holder<SoundEvent> BOOKSHELF_SOUND = SOUND_REGISTER.register(
            "bookshelf_sound",
            SoundEvent::createVariableRangeEvent
    );
}
