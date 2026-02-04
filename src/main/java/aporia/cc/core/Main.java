package aporia.cc.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import ru.kotopushka.compiler.sdk.annotations.Compile;
import ru.kotopushka.compiler.sdk.annotations.CompileBytecode;
import ru.kotopushka.compiler.sdk.annotations.Initialization;
import ru.kotopushka.compiler.sdk.annotations.VMProtect;
import ru.kotopushka.compiler.sdk.enums.VMProtectType;
import aporia.cc.api.file.exception.FileProcessingException;
import aporia.cc.api.repository.box.BoxESPRepository;
import aporia.cc.api.repository.rct.RCTRepository;
import aporia.cc.api.repository.way.WayRepository;
import aporia.cc.api.system.discord.DiscordManager;
import aporia.cc.api.feature.draggable.DraggableRepository;
import aporia.cc.api.file.*;
import aporia.cc.api.repository.macro.MacroRepository;
import aporia.cc.api.event.EventManager;
import aporia.cc.api.feature.module.ModuleProvider;
import aporia.cc.api.feature.module.ModuleRepository;
import aporia.cc.api.feature.module.ModuleSwitcher;
import aporia.cc.api.system.sound.SoundManager;
import aporia.cc.common.util.logger.LoggerUtil;
import aporia.cc.common.util.render.ScissorManager;
import aporia.cc.core.client.ClientInfo;
import aporia.cc.core.client.ClientInfoProvider;
import aporia.cc.core.listener.ListenerRepository;
import aporia.cc.implement.features.commands.CommandDispatcher;
import aporia.cc.implement.features.commands.manager.CommandRepository;
import aporia.cc.implement.features.modules.combat.killaura.attack.AttackPerpetrator;
import aporia.cc.implement.screens.menu.MenuScreen;

import java.io.File;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Main implements ModInitializer {

    @Getter
    static Main instance;
    EventManager eventManager = new EventManager();
    ModuleRepository moduleRepository;
    ModuleSwitcher moduleSwitcher;
    CommandRepository commandRepository;
    CommandDispatcher commandDispatcher;
    BoxESPRepository boxESPRepository = new BoxESPRepository(eventManager);
    MacroRepository macroRepository = new MacroRepository(eventManager);
    WayRepository wayRepository = new WayRepository(eventManager);
    RCTRepository RCTRepository = new RCTRepository(eventManager);
    ModuleProvider moduleProvider;
    DraggableRepository draggableRepository;
    DiscordManager discordManager;
    FileRepository fileRepository;
    FileController fileController;
    ScissorManager scissorManager = new ScissorManager();
    ClientInfoProvider clientInfoProvider;
    ListenerRepository listenerRepository;
    AttackPerpetrator attackPerpetrator = new AttackPerpetrator();
    boolean initialized;

    @Override
    @CompileBytecode
    public void onInitialize() {
        instance = this;

        initClientInfoProvider();
        initModules();
        initDraggable();
        initFileManager();
        initCommands();
        initListeners();
        initDiscordRPC();
        SoundManager.init();
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.initialize();

        initialized = true;
    }

    @Compile
    @Initialization
    private void initDraggable() {
        draggableRepository = new DraggableRepository();
        draggableRepository.setup();
    }

    @Compile
    @Initialization
    private void initModules() {
        moduleRepository = new ModuleRepository();
        moduleRepository.setup();
        moduleProvider = new ModuleProvider(moduleRepository.modules());
        moduleSwitcher = new ModuleSwitcher(moduleRepository.modules(), eventManager);
    }

    @Compile
    @Initialization
    private void initCommands() {
        commandRepository = new CommandRepository();
        commandDispatcher = new CommandDispatcher(eventManager);
    }


    private void initDiscordRPC() {
        discordManager = new DiscordManager();
        discordManager.init();
    }


    private void initClientInfoProvider() {
        File clientDirectory = new File(MinecraftClient.getInstance().runDirectory, "\\zenith\\");
        File filesDirectory = new File(clientDirectory, "\\files\\");
        File moduleFilesDirectory = new File(filesDirectory, "\\config\\");
        clientInfoProvider = new ClientInfo("ZENITH", "FABOS", "ADMIN", clientDirectory, filesDirectory, moduleFilesDirectory);
    }

    private void initFileManager() {
        DirectoryCreator directoryCreator = new DirectoryCreator();
        directoryCreator.createDirectories(clientInfoProvider.clientDir(), clientInfoProvider.filesDir(), clientInfoProvider.configsDir());
        fileRepository = new FileRepository();
        fileRepository.setup(this);
        fileController = new FileController(fileRepository.getClientFiles(), clientInfoProvider.filesDir(), clientInfoProvider.configsDir());
        try {
            fileController.loadFiles();
        } catch (FileProcessingException e) {
            LoggerUtil.error("Error occurred while loading files: " + e.getMessage() + " " + e.getCause());
        }
    }


    private void initListeners() {
        listenerRepository = new ListenerRepository();
        listenerRepository.setup();
    }
}
