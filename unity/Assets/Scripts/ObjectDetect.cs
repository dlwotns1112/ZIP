using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObjectDetect : MonoBehaviour
{
    Transform target;
    [SerializeField]
    private GameObject Btn;
    [SerializeField]
    private Camera camera;

    private bool check;
    private void OnDrawGizmos() { 
        Gizmos.color = Color.white;
        Gizmos.DrawSphere(transform.position, 5f);
    }
    private void Start()
    {
        InvokeRepeating("UpdateTarget", 0f, 0.25f);
    }
    private void Update()
    {
        Vector3 screenPos = camera.WorldToScreenPoint(transform.position + new Vector3(0, 1f, 0));
        Btn.transform.position = screenPos;
    }

    private void UpdateTarget()
    {
        Collider[] cols = Physics.OverlapSphere(transform.position, 3f);
        check = false;
        if (cols.Length > 2)
        {

            for (int i = 0; i < cols.Length; i++)
            {
                if (cols[i].tag == "Player")
                {
                    check = true;
                    target = cols[i].gameObject.transform;
                }
            }
            if (check)
            {
                Btn.SetActive(true);
            }
            else
            {
                Btn.SetActive(false);
                target = null;
            }
        }
    }

}