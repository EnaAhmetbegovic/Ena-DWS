export default [
  {
    label: ' ',
    content: JSON.parse(
      `[
  {
    "label": "Početna",
    "to": "/HomePage"
  },
  {
    "label": "Članovi",
    "content": [
      {
        "label": "Spisak članova",
        "to": "/clanovi"
      },
      {
        "label": "Unos novog člana",
        "to": "/unos-clana"
      }
    ]
  },
  {
    "label": "Jedinice",
    "content": [
      {
        "label": "Spisak jedinica",
        "to": "/jedinice"
      }
    ]
  },
  {
    "label": "Aktivnosti",
    "content": [
      {
        "label": "Spisak aktivnosti",
        "to": "/aktivnosti"
      }
    ]
  }
]`
    )
  }
];
