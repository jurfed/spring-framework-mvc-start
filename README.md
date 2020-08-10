

#### Spring mvc start

@RestController

    @RequestMapping(
            value = "/personId",
            method = RequestMethod.GET
    )
    
     @GetMapping("/personName/{name}")
     
     public @ResponseBody List<Person> savePerson(@RequestBody List<Person> person) {            
            
            