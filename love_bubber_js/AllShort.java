class Solution{
    public:
    void bubbleSort(vector<int>&arr){
        int n=arr.size();
      for(int i=0;i<n-1;i++){
          for(int j=0;j<n-i-1;j++){
              if(arr[j]>arr[j+1])
                swap(arr[j],arr[j+1]);
          }
      }
    }
    void insertionSort(vector<int>&arr){
        int n=arr.size();
        int i=0;
        while(i<n-1){
            int j=i+1;
            int k=i;
            while(k>=0 && arr[k]>arr[j]){
                swap(arr[k],arr[j]);
                j--;
                k--;
            }
            i++;
        }
    }
    void selectionSort(vector<int>&arr){
        int n=arr.size();
        for(int i=0;i<n-1;i++){
              int k=i;
            for(int j=i+1;j<n;j++){
                if(arr[k]>arr[j])
                   k=j;
            }
            swap(arr[k],arr[i]);
        }
    }
    int partition(vector<int>&arr,int l,int r){
        int pivot=arr[r];
        int i=l-1;
        for(int j=l;j<r;j++)
            if(arr[j]<pivot)
                swap(arr[++i],arr[j]);
        swap(arr[++i],arr[r]);
    return i;
    }
    void quickSort(vector<int>&arr,int l,int r){
        if(l<r){
            int p=partition(arr,l,r);
            quickSort(arr,l,p-1);
            quickSort(arr,p+1,r);
        }
        
    }
    
    void countSort(vector<int>&arr){
        int maxi=0;
        int n=arr.size();
        for(int &it: arr)
           maxi=max(it,maxi);
        vector<int>count(maxi+1,0);
        for(int &it: arr)
           count[it]++;
        for(int i=1;i<=maxi;i++)
           count[i]+=count[i-1];
        vector<int>output(n,0);
        for(int i=n-1;i>=0;i--)
          output[--count[arr[i]]]=arr[i];
        
        for(int i=0;i<n;i++)
          arr[i]=output[i];
         
    }
    void Radix(vector<int>&arr,vector<int>&temp,int exp){
        int count[10]={0};
        for(int &it: arr)
          count[(it/exp)%10]++;
        for(int i=1;i<10;i++)
          count[i]+=count[i-1];
        for(int i=arr.size()-1;i>=0;i--)
          temp[--count[(arr[i]/exp)%10]]=arr[i];
        for(int i=0;i<arr.size();i++)
          arr[i]=temp[i];
    }
    void radixSort(vector<int>&arr){
        int maxi=0;
        int n=arr.size();
        for(int &it: arr)
           maxi=max(maxi,it);
        vector<int>temp(n,0);
        for(int exp=1;maxi/exp>0;exp*=10)
          Radix(arr,temp,exp);
    }
    void merge(vector<int>&arr,vector<int>&temp,int l,int m,int r){
        int i=l,j=m+1,k=l;
        while(i<=m && j<=r){
            if(arr[i]<=arr[j])
              temp[k++]=arr[i++];
            else
              temp[k++]=arr[j++];
        }
        while(i<=m)temp[k++]=arr[i++];
        while(j<=r)temp[k++]=arr[j++];
      for(int a=l;a<=r;a++)
         arr[a]=temp[a];
    }
    void mergeSort(vector<int>&arr,vector<int>&temp,int l,int r){
        if(l<r){
            int m=(l+r)/2;
            mergeSort(arr,temp,l,m);
            mergeSort(arr,temp,m+1,r);
            merge(arr,temp,l,m,r);
        }
    }
  
    int nextgap(int n){
        int gap=(n*10)/13;
        if(gap<1)
           return 1;
        return gap;
    }
    
    void combSort(vector<int>&arr){
        int n=arr.size();
        bool swapped=true;
        int gap=n;
        while(gap!=1 || swapped){
            swapped=false;
            gap=nextgap(gap);
            for(int i=0;i<n-gap;i++){
                if(arr[i]>arr[i+gap]){
                    swapped=true;
                    swap(arr[i],arr[i+gap]);
                }
            }
        }
    }
    
    void shellSort(vector<int>&arr){
        int n=arr.size();
        //12 14 61 23 6 24 2
        for(int gap=n/2;gap>0;gap/=2){
            for(int i=gap;i<n;i++){
                int temp=arr[i];
                int j=i;
                while(j>=gap && arr[j-gap]>temp){
                   arr[j]=arr[j-gap];
                   j-=gap;
                }
                arr[j]=temp;
            }
        }
    }
    
    void bucketSort(vector<int>&arr){
        int n=arr.size();
        int maxi=0;
        int mini=arr[0];
        for(int &it: arr){
            maxi=max(maxi,it);
            mini=min(mini,it);
        }
        vector<int>bucket(maxi-mini+1,0);
        for(int &it: arr)
          bucket[it-mini]++;
         vector<int>res;
        for(int i=0;i<bucket.size();i++){
            while(bucket[i]>0){
                res.push_back(i+mini);
                bucket[i]--;
            }
        }
        for(int i=0;i<n;i++)
           arr[i]=res[i];
    }
    
    vector<int> sortArr(vector<int>arr, int n){
     //selectionSort(arr);
     //bubbleSort(arr);
     //insertionSort(arr);
     //quickSort(arr,0,n-1);
     //countSort(arr);
     //radixSort(arr);
     //  vector<int>temp(n,0);
     //  mergeSort(arr,temp,0,n-1);
     //combSort(arr);
     //shellSort(arr);
     bucketSort(arr);
     return arr;
    }
};