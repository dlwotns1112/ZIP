using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;
using TMPro;
using UnityEngine.U2D;

public class ShopListItem : MonoBehaviour
{
    public Image imgIcon;
    public TMP_Text priceText;
    public Button itemButton;
    public SpriteAtlas atlas;

    public void Init(long id, string img, int price, long posId)
    {
        // ������ �ֱ� 
        var spriteName = atlas.GetSprite(img);
        this.imgIcon.sprite = spriteName;
        this.priceText.text = price.ToString();

        this.itemButton.onClick.AddListener(() => OnClickButton(id, posId));
    }

    public void OnClickButton(long itemId, long posId)
    {
        // ���� api ����
        Debug.Log("shopitem OnClickButton: " + itemId + ", " + posId);
    }
}