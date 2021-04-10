using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playbackspeed : MonoBehaviour
{
    //Define Animation component
    public Animation anim;
    
    void Start()
    {
        SondaManager.inserisciSonda("playbackspeed.Start");
        // Set playback speed of "lift" animation to 0.25 (floating point)
        anim["lift"].speed = 0.25f;
    }
}