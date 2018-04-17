
int VerificaNumero() 
{ 
    int num; 
    string s; 
     
    printf ("Digite um número: "); 
    scanf ("%d",&num); 
     
    if (num>10) 
    { 
        printf ("\n\n O número é maior que 10"); 
        s = "errou"; 
    } 
    if (num==10) 
    { 
        printf ("\n\n Você acertou!\n"); 
        printf ("O numero é igual a 10."); 
        s = "acertou"; 
    } 
    if (num<10) 
    { 
        printf ("\n\n O número é menor que 10"); 
        s = "errou"; 
    }     
    if(num == 10 && s == "acertou") 
    { 
        return 1; 
    } 
    return 0; 
} 