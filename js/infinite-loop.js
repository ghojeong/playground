const func1 = (func) => {
    console.log("Render 1")
    func()
}

const func2 = () => {
    console.log("Render 2")
    func1(func2)
}

func2()

