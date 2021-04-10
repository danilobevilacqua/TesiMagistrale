using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class translate : MonoBehaviour
{
    // Define the dropdown GameObject
    public Dropdown myDropdown;

    // Define the sign for each language
    public GameObject LIFTItalian;
    public GameObject LIFTLithuanian;
    public GameObject LIFTUrdu;
    public GameObject FALLItalian;
    public GameObject FALLLithuanian;
    public GameObject FALLUrdu;

    void Start()
    {
        SondaManager.inserisciSonda("translate.Start");
        // Create new event - On dropdown value change
        myDropdown.onValueChanged.AddListener(delegate {
            myDropdownValueChangedHandler(myDropdown);
        });
    }

    void Destroy()
    {
        SondaManager.inserisciSonda("translate.Destroy");
        // Destroy event as soon as user quits the app
        myDropdown.onValueChanged.RemoveAllListeners();
    }

    // When dropdown value changes
    private void myDropdownValueChangedHandler(Dropdown target)
    {
        SondaManager.inserisciSonda("translate.myDropdownValueChangedHandler");
        // If "English" is selected
        if (target.value == 0)
        {
            SondaManager.inserisciSonda("translate.myDropdownValueChangedHandler.if (target.value == 0)");
            // Hide translated signage
            LIFTItalian.SetActive(false);
            LIFTLithuanian.SetActive(false);
            LIFTUrdu.SetActive(false);
            FALLItalian.SetActive(false);
            FALLLithuanian.SetActive(false);
            FALLUrdu.SetActive(false);
        }

        // If "Italian" is selected
        if (target.value == 1)
        {
            SondaManager.inserisciSonda("translate.myDropdownValueChangedHandler.if (target.value == 1)");
            // Hide non-Italian signage
            // (The English sign is the marker itself, thus the Italian signage will be displayed as an overlay)
            LIFTItalian.SetActive(true);
            LIFTLithuanian.SetActive(false);
            LIFTUrdu.SetActive(false);
            FALLItalian.SetActive(true);
            FALLLithuanian.SetActive(false);
            FALLUrdu.SetActive(false);
        }

        // If "Lithuanian" is selected
        if (target.value == 2)
        {
            SondaManager.inserisciSonda("translate.myDropdownValueChangedHandler.if (target.value == 2)");
            // Hide non-Lithuanian signage
            LIFTItalian.SetActive(false);
            LIFTLithuanian.SetActive(true);
            LIFTUrdu.SetActive(false);
            FALLItalian.SetActive(false);
            FALLLithuanian.SetActive(true);
            FALLUrdu.SetActive(false);
        }

        // If "Urdu" is selected
        if (target.value == 3)
        {
            SondaManager.inserisciSonda("translate.myDropdownValueChangedHandler.if (target.value == 3)");
            // Hide non-Urdu signage
            LIFTItalian.SetActive(false);
            LIFTLithuanian.SetActive(false);
            LIFTUrdu.SetActive(true);
            FALLItalian.SetActive(false);
            FALLLithuanian.SetActive(false);
            FALLUrdu.SetActive(true);
        }
    }

    // Set the index for each value in the dropdown
    public void SetDropdownIndex(int index)
    {
        SondaManager.inserisciSonda("translate.SetDropdownIndex");
        myDropdown.value = index;
    }
}
