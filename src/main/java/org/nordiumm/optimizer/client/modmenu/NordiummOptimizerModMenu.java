package org.nordiumm.optimizer.client.modmenu;

import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;

import org.nordiumm.optimizer.client.config.OptimizerConfigScreen;

public class NordiummOptimizerModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return OptimizerConfigScreen::new;
    }
}