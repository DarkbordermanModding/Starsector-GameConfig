package mod.gameconfig;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI.ShipTypeHints;
import com.fs.starfarer.api.campaign.FactionSpecAPI;
import org.json.JSONException;
import org.json.JSONObject;


public class GameConfigPlugin extends BaseModPlugin {

    public void onGameLoad(boolean newGame) {
        for (FactionSpecAPI faction : Global.getSettings().getAllFactionSpecs()) {
            try {
                JSONObject custom = faction.getCustom();
                if (custom == null) continue;
                custom.put("caresAboutAtrocities", false);
                faction.setCustom(custom);
            } catch (JSONException e) {
                Global.getLogger(GameConfigPlugin.class).warn("Failed to set caresAboutAtrocities for faction: " + faction.getId(), e);
            }
        }
    }
}
