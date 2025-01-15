function App() {
  const scrollId = Array.from({ length: 3 }, (_, i) => `${i + 1}`)

  return (
    <>
      <div className="sticky bg-black text-white top-0 flex flex-row">
        {scrollId.map((id) => (
          <a href={`#${id}`} className="block p-4">
            {id}
          </a>
        ))}
      </div>
      <div
        className="bg-rainbow w-screen h-fit flex items-center justify-center flex-col"
        id="1"
      >
        <div className="grid grid-cols-2 place-content-center justify-center items-center group">
          <img
            className="h-[20rem] aspect-square object-fill animate-none group-hover:animate-spin rounded-full"
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZay0ZOAmHch_E8ftHzO3wvDqBhRBb67HTXg&s"
          />
          <div className="text-[4rem] hover:animate-spin font-bold group-hover:bg-rainbow">
            mahalnw Beam
          </div>
        </div>
        <div className="text-[10rem] font-bold">My experience</div>
        <ul id="2">
          <li className="text-4xl">
            Current Work as Software Engineer & Fullstack web developer :
            Wirabyte (JUN to Todays)
          </li>
        </ul>
        <div className="text-[10rem] font-bold">My Education</div>
        <ul id="3">
          <li className="text-4xl">
            KING MONGKUT UNIVERSITY OF TECHNOLOGY THONBURI (2024-PRESENT)
          </li>
          <li className="text-4xl">PETPRACHOMKLAO SCHOLARSHIP (INOVENTION)</li>
        </ul>
        <div className="text-[10rem] font-bold" id="4">
          My Skills
        </div>
        <div className="text-[10rem]">
          REACT, TAILWIND, TYPESCRIPT, JAVASCRIPT, NODE JS
        </div>
        <div className="text-[5rem]">I DO EVERYTHING!!!!!!!!!!!!!</div>
      </div>
    </>
  )
}

export default App
